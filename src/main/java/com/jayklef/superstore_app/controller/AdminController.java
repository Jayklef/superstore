package com.jayklef.superstore_app.controller;

import com.jayklef.superstore_app.entity.Admin;
import com.jayklef.superstore_app.entity.Order;
import com.jayklef.superstore_app.entity.Product;
import com.jayklef.superstore_app.entity.User;
import com.jayklef.superstore_app.service.AdminService;
import com.jayklef.superstore_app.service.OrderService;
import com.jayklef.superstore_app.service.ProductService;
import com.jayklef.superstore_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    private User user;

    @GetMapping("/verify/credentials")
    public String verifyAdmin(@ModelAttribute("admin") Admin admin, Model model) {
        if (adminService.verifyCredentials(admin.getEmail(), admin.getPassword())) {
            return "admin/home";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "Login";
        }
    }

    @GetMapping("/admin/home")
    public String adminHomePage(Model model) {
        model.addAttribute("admins", adminService.getAllAdmins());
        model.addAttribute("users", userService.getAllAdmins());
        model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("orders", orderService.getAllOrders());

        return "adminHomePage";
    }
    @GetMapping("/add/admin")
    public String addAdminPage() {
        return "addAdmin";
    }

    @PostMapping("/add/admin")
    public String addAdmin(Admin admin) {
        adminService.createAdmin(admin);
        return "/admin/home";
    }

    @GetMapping("/update/admin/{id}")
    public String updateAdminPage(@PathVariable Long id, Model model) {
        model.addAttribute("admin", adminService.getAdminById(id));
        return "updateUser";
    }

    @PostMapping("/update/admin")
    public String updateAdmin(Admin admin) {
        adminService.updateAdmin(admin);
        return "/admin/home";
    }

    @DeleteMapping("/delete/admin/{id}")
    public String deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return "admin/home";
    }

    @GetMapping("/user/login")
    public String userLoginPage(User user, Model model) {
        if (userService.verifyCredentials(user.getEmail(), user.getPassword())) {
            user = userService.getUserByEmail(user.getEmail());
            model.addAttribute("ordersList", orderService.getOrdersByUserName(user));
            return "ProductPage";
        } else {
            model.addAttribute("error", "Invalid email or password");
        }
        model.addAttribute("error", "Invalid email or password");
        return "Login";
    }


    @GetMapping("/products/search")
    public String productSearch(String name, Model model) {
        Product product = productService.findProductByName(name);
        if (product != null) {
            model.addAttribute("ordersList", orderService.getOrdersByUserName(user));
            model.addAttribute("product", product);

            return "ProductPage"; // Return the view name for product details
        } else {
            model.addAttribute("error", "Sorry, Product is not found");
            model.addAttribute("ordersList", orderService.getOrdersByUserName(user));
        }
        return "ProductPage"; // Return the view name for search results
    }


    @GetMapping("/place/order")
    public String placeOrderPage(Order order, Model model) {
        double totalAmount = order.getPrice() * order.getQuantity();
        order.setAmount(totalAmount);
        order.setUser(user);
        order.setOrderDate(new Date());

        orderService.createOrder(order);
        model.addAttribute("amount", totalAmount);
        return "OrderStatus";

    }
}
