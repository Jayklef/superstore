package com.jayklef.superstore_app.controller;

import com.jayklef.superstore_app.entity.Admin;
import com.jayklef.superstore_app.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private ProductService productService;
    @GetMapping({"/", "/home" })
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/products")
    public String productsPage(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "ProductPage";
    }



    @GetMapping("/contactUs")
    public String contactPage() {
        return "contactPage";
    }

    @GetMapping("/about")
    public String aboutUs() {
        return "aboutUs";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("admin", new Admin());
        return "Login";
    }

}
