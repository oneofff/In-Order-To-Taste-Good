package com.pluralsight.model.order;

import com.pluralsight.model.extras.Chips;
import com.pluralsight.model.extras.Drink;
import com.pluralsight.model.sandwich.Sandwich;

import java.util.List;

public abstract class OrderItem implements Comparable<OrderItem> {

    public abstract List<String> getRepresentation();

    public abstract List<String> getShortRepresentation();

    public abstract double getTotalPrice();

    private int getSortOrder() {
        if (this instanceof Sandwich) {
            return 1;
        } else if (this instanceof Drink) {
            return 2;
        } else if (this instanceof Chips) {
            return 3;
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public int compareTo(OrderItem other) {
        return Integer.compare(this.getSortOrder(), other.getSortOrder());
    }
}
