package com.jayklef.superstore_app.service;

import com.jayklef.superstore_app.entity.Admin;
import com.jayklef.superstore_app.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin with id: " + id + " not found"));
    }

    public Admin updateAdmin(Admin admin){
        Admin adminToUpdate = adminRepository.findById(admin.getId())
                .orElseThrow(() -> new RuntimeException("Admin with id: " + admin.getId() + " not found"));
        adminRepository.save(adminToUpdate);
        return adminToUpdate;
    }

    public void deleteAdmin(Long id) {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Admin with id: " + id + " not found"));
        adminRepository.delete(admin);
    }

    public boolean verifyCredentials(String email, String password) {
        Admin admin = adminRepository.findByEmail(email);
        if (admin != null && admin.getPassword().equals(password)) {
            return true;
        }
        return false;
    }


}
