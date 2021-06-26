package com.example.demo.Impl;

import com.example.demo.model.Users;
import com.example.demo.respository.UsersRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersRepositoryImpl implements UsersRepositoryCustom {
    @Autowired
    private  MongoTemplate mongoTemplate;
    @Override
    public List<Users> login(String userName,String password) {
        Query q= new Query();
        final List<Criteria> criteria = new ArrayList<>();
            criteria.add(new Criteria("userName").is(userName));
            criteria.add(new Criteria("password").is(password));

        q.addCriteria(new Criteria().andOperator(criteria));
        return  mongoTemplate.find(q,Users.class);
    }
}
