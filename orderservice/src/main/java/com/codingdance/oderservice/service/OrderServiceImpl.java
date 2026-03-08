package com.codingdance.orderservice.service;

import com.codingdance.orderservice.client.ProductClient;
import com.codingdance.orderservice.entity.OrderEntity;
import com.codingdance.orderservice.repository.OrderRepository;
import com.codingdance.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductClient productClient;
    @Override
    public OrderEntity placeOrder(Long productId, Integer quantity) {
        Double unitPrice = 100.0; 
        Double totalPrice = unitPrice * quantity;
        OrderEntity order = new OrderEntity();
        order.setProductId(productId.toString());
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice);

        return orderRepository.save(order);
    }

    @Override
    public OrderEntity getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}
