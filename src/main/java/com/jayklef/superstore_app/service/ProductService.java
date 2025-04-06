package com.jayklef.superstore_app.service;

import com.jayklef.superstore_app.entity.Product;
import com.jayklef.superstore_app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found"));
    }

    public void updateProduct(Product product) {
        Product productToUpdate = productRepository.findById(product.getId())
                .orElseThrow(() -> new RuntimeException("Product with id: " + product.getId() + " not found"));
        productRepository.save(productToUpdate);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product with id: " + id + " not found"));
        productRepository.delete(product);
    }
}
