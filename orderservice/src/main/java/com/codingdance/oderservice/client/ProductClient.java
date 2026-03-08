package com.codingdance.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// name 必須對應 Product 服務在 Eureka 上的名字
@FeignClient(name = "PRODUCT-SERVICE") 
public interface ProductClient {

    // 這裡的方法簽名必須跟 ProductService 裡的 Controller 一模一樣
    @GetMapping("/products/{id}")
    Object getProductById(@PathVariable("id") Long id); 
}
