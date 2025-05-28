package com.pluralsight.model.order;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

@Data
public class Order {
    private LocalDateTime orderDate;
    private SortedSet<OrderItem> items = new TreeSet<>((OrderItem::compareTo));

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
        details.add("Your order:");
        details.add("-".repeat(10));
        for (OrderItem item : items) {
            details.addAll(item.getShortRepresentation());
            details.add("-".repeat(10));
        }
        details.add(String.format("Total Price: %.2f", getTotalPrice()));
        return details;
    }
}
