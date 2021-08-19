package com.example.demo.controller;

import com.example.demo.model.Orders;
import com.example.demo.respository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;

@RequestMapping(value="/orders")
@Controller
public class OrdersController {
    @Autowired
    OrdersRepository orders;
    @PreAuthorize("hasAuthority('USER')")
    @RequestMapping(value="/create",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Orders> createOrder(@Valid @RequestBody HashMap<String,String> body){
        String userId=body.get("userId");
        String productId=body.get("productId");
        Orders newOrder=orders.createOrder(userId,productId);
        return new ResponseEntity<Orders>(newOrder,HttpStatus.OK);
    }
}
