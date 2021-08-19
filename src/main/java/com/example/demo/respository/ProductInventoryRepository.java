package com.example.demo.respository;

import com.example.demo.model.ProductsInventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductInventoryRepository extends MongoRepository<ProductsInventory,String> {
}
