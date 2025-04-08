package com.jayklef.superstore_app.service;

import com.jayklef.superstore_app.entity.Admin;
import com.jayklef.superstore_app.entity.User;
import com.jayklef.superstore_app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllAdmins() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        userRepository.save(user);
    }
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id: " + id + " not found"));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateAdmin(User user){
        User adminToUpdate = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User with id: " + user.getId() + " not found"));
        userRepository.save(adminToUpdate);
        return adminToUpdate;
    }

    public void deleteAdmin(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin with id: " + id + " not found"));
        userRepository.delete(user);
    }

    public boolean verifyCredentials(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

}
