package com.example.demo.respository;

import com.example.demo.model.Orders;

public interface OrdersRepositoryCustom {
   Orders createOrder(String userId,String productId);
}
