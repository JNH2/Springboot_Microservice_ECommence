package com.codingdance.orderservice.service;
import com.codingdance.orderservice.entity.OrderEntity;


public interface OrderService {
    OrderEntity placeOrder(Long productId, Integer quantity);
    OrderEntity getOrderDetails(Long orderId);

    
}
