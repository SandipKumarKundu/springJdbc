package com.example.demo.Impl;

import com.example.demo.model.Orders;
import com.example.demo.model.ProductsInventory;
import com.example.demo.model.Users;
import com.example.demo.respository.OrdersRepositoryCustom;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationPipeline;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Component;

import javax.management.Query;
import java.util.ArrayList;
import java.util.Arrays;
@Slf4j
@Component
public class OrdersRepositoryImpl implements OrdersRepositoryCustom {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Orders createOrder(String userId, String productId) {
        System.out.println("Create order called");
        Orders or=mongoTemplate.save(new Orders(userId,productId),mongoTemplate.getCollectionName(Orders.class));
        Aggregation agg = Aggregation.newAggregation(l ->new Document("$lookup",
                        new Document("from", mongoTemplate.getCollectionName(ProductsInventory.class))
                                .append("let", new Document("productId", productId))
                                .append("pipeline",
                                        Arrays.asList(new Document("$match",
                                                new Document("$expr",
                                                        new Document("$eq", Arrays.asList("$name", "$$productId"))))))
                                .append("as", "productDetails")),
                Aggregation.unwind("productDetails", Boolean.TRUE), Aggregation.unwind("userDetails", Boolean.TRUE));

                agg.getPipeline().add(l->new Document("$lookup",
                new Document("from", mongoTemplate.getCollectionName(Users.class))
                        .append("let", new Document("userId", userId))
                        .append("pipeline",
                                Arrays.asList(new Document("$match",
                                        new Document("$expr",
                                                new Document("$eq", Arrays.asList("$userName", "$$userId"))))))
                        .append("as", "userDetails")));
                agg.getPipeline()
                        .add(l->new Document("$match",
                                new Document("$expr",new Document("$and",Arrays.asList(
                                        new Document("$eq", Arrays.asList("$userId", userId)),
                                        new Document("$eq", Arrays.asList("$productId", productId))
                                )))));

        AggregationResults<Orders> results  = mongoTemplate.aggregate(agg,
                mongoTemplate.getCollectionName(Orders.class), Orders.class);
log.info("Order Created for {} for {}",userId,productId);
        return  results.getMappedResults().get(0);
    }
}
