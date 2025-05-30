package com.pluralsight.service;

import com.pluralsight.model.order.Order;
import com.pluralsight.repository.FileOrderRepository;
import com.pluralsight.repository.OrderRepository;
import com.pluralsight.service.interfaces.OrderService;

public class DefaultOrderService implements OrderService {
    private final OrderRepository orderRepository;

    public DefaultOrderService() {
        this(FileOrderRepository.getInstance());
    }

    DefaultOrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void saveOrder(Order order) {
        if (order == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Order details cannot be null or empty.");
        }
        orderRepository.saveOrder(order);
    }
} 