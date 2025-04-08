package com.jayklef.superstore_app.repository;

import com.jayklef.superstore_app.entity.Order;
import com.jayklef.superstore_app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
