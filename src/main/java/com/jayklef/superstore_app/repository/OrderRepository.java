package com.jayklef.superstore_app.repository;

import com.jayklef.superstore_app.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByName(String name);
}
