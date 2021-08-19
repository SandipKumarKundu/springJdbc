package com.example.demo.respository;

import com.example.demo.model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrdersRepository extends MongoRepository<Orders, String> ,OrdersRepositoryCustom {
}
