package com.pluralsight.model.order;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order {
    private LocalDateTime orderDate;
    private List<OrderItem> items;

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(OrderItem::getPrice)
                .sum();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public String getOrderDetails() {
        StringBuilder details = new StringBuilder("Order Details:\n");
        for (OrderItem item : items) {
            details.append(item.getDescription()).append(": $").append(item.getPrice()).append("\n");
        }
        details.append("Total Price: $").append(getTotalPrice());
        return details.toString();
    }
}
