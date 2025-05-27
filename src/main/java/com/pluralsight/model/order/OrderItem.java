package com.pluralsight.model.order;

import java.util.List;

public abstract class OrderItem {

    public abstract List<String> getRepresentation();

    public abstract double getTotalPrice();
}
