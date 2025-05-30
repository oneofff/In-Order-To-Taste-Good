package com.pluralsight.service.interfaces;

import com.pluralsight.model.order.Order;
import com.pluralsight.model.sandwich.CustomSandwich;

public interface SandwichService {
    void addCustomSandwichToOrder(Order order, CustomSandwich customSandwich);
}
