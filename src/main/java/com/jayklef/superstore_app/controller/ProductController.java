package com.jayklef.superstore_app.controller;

import com.jayklef.superstore_app.entity.Product;
import com.jayklef.superstore_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/add/products")
    public String addProduct() {
        return "addProduct";
    }
    @PostMapping("/add/products")
    public String addProduct(Product product) {
        productService.addProduct(product);
        return "admin/home";
    }
    @GetMapping("/update/products/{id}")
    public String updateProductPage(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "updateProduct";
    }

    @PostMapping("/update/product")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "admin/home";
    }

    @DeleteMapping("/delete/products/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "admin/home";
    }
}
