package com.pluralsight.model.order;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        representation.addAll(getCostRepresentation());
        return representation;
    }

    private List<String> getCostRepresentation() {
        return List.of(
                String.format("Gratuity (%.2f%%): $%.2f", getGratuityPercentage(), gratuity),
                String.format("Tax (%.2f%%): $%.2f", TAX_RATE * 100, getTaxAmount()),
                String.format("Total Price: $%.2f", getTotalPrice())
        );
    }

    private double getGratuityPercentage() {
        return Math.round(gratuity / getPriceWithTaxes() * 100);
    }

    public String getCheckRepresentation() {
        return "Order Date: " + orderDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' h:mma")) + "\n" +
                String.join("\n", getOrderRepresentation()) +
                "\n" +
                String.join("\n", getCostRepresentation());
    }
}
