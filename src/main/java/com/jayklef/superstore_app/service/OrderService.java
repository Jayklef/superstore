package com.jayklef.superstore_app.service;

import com.jayklef.superstore_app.entity.Order;
import com.jayklef.superstore_app.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void addOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id: " + id + " not found"));
    }

    public void updateOrder(Order order) {
        Order orderToUpdate = orderRepository.findById(order.getId())
                .orElseThrow(() -> new RuntimeException("Order with id: " + order.getId() + " not found"));
        orderRepository.save(orderToUpdate);
    }

    public void deleteOrder(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Order with id: " + id + " not found"));
        orderRepository.delete(order);
    }
}
