package com.jayklef.superstore_app.controller;

import com.jayklef.superstore_app.entity.Product;
import com.jayklef.superstore_app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @GetMapping("/products")
    public String addProductPage() {
        return "addProduct";
    }
}
