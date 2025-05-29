package com.pluralsight.repository;

import com.pluralsight.model.order.Order;

public interface OrderRepository {

    void saveOrder(Order order);
}
