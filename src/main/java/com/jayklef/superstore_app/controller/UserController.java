package com.jayklef.superstore_app.controller;

import com.jayklef.superstore_app.entity.User;
import com.jayklef.superstore_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/add/user")
    public String addUser() {
        return "addUser";
    }

    @PostMapping("/add/user")
    public String addUser(User user) {
        userService.createUser(user);
        return "Login";
    }

    @GetMapping("/update/user/{id}")
    public String updateUserPage(@PathVariable Long id, Model model) {
        model.addAttribute("admin", userService.getUserById(id));
        return "updateUser";
    }

    @PostMapping("/update/user")
    public String updateUser(User user) {
        userService.createUser(user);
        return "admin/home";
    }

    @DeleteMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteAdmin(id);
        return "admin/home";
    }
}
