package com.codingdance.orderservice.controller;

import com.codingdance.orderservice.entity.OrderEntity;
import com.codingdance.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController // 
@RequestMapping("/orders") // 
public class OrderController {

    @Autowired
    private OrderService orderService; // 

    @PostMapping("/placeOrder")
    public OrderEntity createOrder(@RequestParam Long productId, @RequestParam Integer quantity) {
        return orderService.placeOrder(productId, quantity);
    }

    @GetMapping("/{id}")
    public OrderEntity getOrderById(@PathVariable("id") Long id) {
        return orderService.getOrderDetails(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        return "Order " + id + " has been cancelled.";
    }
}
