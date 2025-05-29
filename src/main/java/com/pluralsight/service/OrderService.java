package com.pluralsight.service;

import com.pluralsight.model.order.Order;
import com.pluralsight.repository.FileOrderRepository;
import com.pluralsight.repository.OrderRepository;

public class OrderService {

    private final OrderRepository orderRepository = FileOrderRepository.getInstance();

    public void saveOrder(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order details cannot be null or empty.");
        }
        orderRepository.saveOrder(order);
    }

}
