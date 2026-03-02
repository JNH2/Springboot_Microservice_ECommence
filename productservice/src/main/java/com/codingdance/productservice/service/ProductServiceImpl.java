package com.codingdance.productservice.service;
import com.codingdance.productservice.entity.Product;
import com.codingdance.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Override
    public String getProductNameById(long id) {
        return productRepository.findById(id).orElseThrow().getProductName();}
    @Override
    public long getProductIdByName(String name) {
        return productRepository.findAll().stream()
                .filter(product -> product.getProductName().equals(name))
                .findFirst()
                .orElseThrow()
                .getId();}
    @Override
    public long getPriceById(long id) {
        return productRepository.findById(id).orElseThrow().getPrice();}
    @Override
    public long getQuantityById(long id) {
        return productRepository.findById(id).orElseThrow().getQuantity();}
    @Override
    public void addProduct(Product product) {
        productRepository.save(product);}
    @Override
    public void updateProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            productRepository.save(product);
        } else {
            throw new RuntimeException("Product not found");}}
    @Override
    public void deleteProduct(long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found");}}
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll(); }}       