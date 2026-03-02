package com.codingdance.productservice.controller; 
import com.codingdance.productservice.entity.Product;
import com.codingdance.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public void addProduct(@RequestBody Product product) {
        productService.addProduct(product);
    }
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
    }
    @GetMapping("/{id}/name")
    public String getProductNameById(@PathVariable long id) {
        return productService.getProductNameById(id);
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/name/{name}/id")
    public long getProductIdByName(@PathVariable String name) {
        return productService.getProductIdByName(name);
    }   
    @GetMapping("/{id}/price")
    public long getPriceById(@PathVariable long id) {
        return productService.getPriceById(id);
    }   
    @GetMapping("/{id}/quantity")
    public long getQuantityById(@PathVariable long id) {
        return productService.getQuantityById(id);
    }
    @PutMapping("/{id}")
    public void updateProduct(@PathVariable long id, @RequestBody Product product) {
        productService.updateProduct(product);
    }
}
