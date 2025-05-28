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
    private double gratuity;
    private double TAX_RATE = 0.0475;

    public double getPriceWithTaxes() {
        return getPriceWithoutTax() + getTaxAmount();
    }

    public double getPriceWithoutTax() {
        return items.stream()
                .mapToDouble(OrderItem::getTotalPrice)
                .sum();
    }

    public double getTaxAmount() {
        return getPriceWithoutTax() * TAX_RATE;
    }

    public double getTotalPrice() {
        return getPriceWithTaxes() + gratuity;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
    }

    public List<String> getOrderRepresentation() {
        List<String> details = new LinkedList<>();
        details.add("Your order:");
        details.add("-".repeat(10));
        for (OrderItem item : items) {
            details.addAll(item.getShortRepresentation());
            details.add("-".repeat(10));
        }
        details.add(String.format("Price: %.2f", getPriceWithoutTax()));

        return details;
    }

    public List<String> getOrderCheckoutRepresentation() {
        List<String> representation = new LinkedList<>();
        representation.add("Order Checkout:");
        representation.add("-".repeat(20));
        representation.addAll(getOrderRepresentation());
        representation.add(String.format("Gratuity: $%.2f", gratuity));
        representation.add(String.format("Tax (%.2f%%): $%.2f", TAX_RATE * 100, getTaxAmount()));
        representation.add(String.format("Total Price: $%.2f", getTotalPrice()));
        return representation;
    }

    public String getCheckRepresentation() {
        return String.join("\n", getOrderCheckoutRepresentation());
    }
}
