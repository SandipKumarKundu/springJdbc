package com.example.demo.respository;

import com.example.demo.model.Carts;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartsRepository extends MongoRepository<Carts,String> {
}
