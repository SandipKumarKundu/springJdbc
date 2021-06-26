package com.example.demo.respository;

import com.example.demo.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface  UsersRepository extends MongoRepository<Users, String> {
    Users findByFirstName(String name);
    Users findByUserNameAndPassword(String userName,String password);
}
