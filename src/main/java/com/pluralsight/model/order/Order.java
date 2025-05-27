package com.pluralsight.model.order;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Data
public class Order {
    private LocalDateTime orderDate;
    private List<OrderItem> items = new LinkedList<>();

    public double getTotalPrice() {
        return items.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public List<String> getOrderDetails() {
        List<String> details = new LinkedList<>();
        details.add("Items in Order:");
        for (OrderItem item : items) {
            details.addAll(item.getRepresentation());
            details.add("Total Price: " + item.getTotalPrice());
        }
        details.add("Total Order Price: " + getTotalPrice());
        return details;
    }
}
