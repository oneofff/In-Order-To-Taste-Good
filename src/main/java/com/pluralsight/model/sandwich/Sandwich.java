package com.pluralsight.model.sandwich;

import com.pluralsight.model.order.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Sandwich extends OrderItem {
    private String name;
    private String breadType;
    private boolean toasted;
    private List<Topping> toppings;
    private List<Sauce> sauces;
}
