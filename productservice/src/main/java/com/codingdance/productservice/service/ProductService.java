package com.codingdance.productservice.service;

import com.codingdance.productservice.entity.Product;
import java.util.List;

public interface ProductService{

    String getProductNameById(long id);

    long getProductIdByName(String name);

    long getPriceById(long id);

    long getQuantityById(long id);

    void addProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(long id);

    List<Product> getAllProducts();
}