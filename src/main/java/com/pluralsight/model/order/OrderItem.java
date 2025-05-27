package com.pluralsight.model.order;

import java.util.List;

public abstract class OrderItem {

    public abstract List<String> getRepresentation();

    public abstract List<String> getShortRepresentation();

    public abstract double getTotalPrice();
}
