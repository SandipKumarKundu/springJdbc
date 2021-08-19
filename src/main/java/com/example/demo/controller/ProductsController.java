package com.example.demo.controller;

import com.example.demo.model.ProductsInventory;
import com.example.demo.respository.ProductInventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping(value = "/products")
@Controller
public class ProductsController {
    @Autowired
    ProductInventoryRepository productInventoryRepository;

    @RequestMapping(value="/create",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductsInventory> createProduct(@RequestBody ProductsInventory body){
        ProductsInventory p=productInventoryRepository.save(body);
        return new ResponseEntity(p, HttpStatus.OK);
    }
    @RequestMapping(value="/get",method= RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductsInventory> getProduct(){
        List<ProductsInventory> p=productInventoryRepository.findAll();
        return new ResponseEntity(p, HttpStatus.OK);
    }
}
