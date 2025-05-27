package com.pluralsight.model.sandwich;

import com.pluralsight.model.order.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class Sandwich extends OrderItem {
    private String name;
    private String size;
    private String bread;
    private double basePrice;
    private boolean isToasted;
    private List<PremiumTopping> premiumToppings = new LinkedList<>();
    private List<RegularTopping> regularToppings = new LinkedList<>();
    private List<Sauce> sauces = new LinkedList<>();

    public List<String> getShortRepresentation() {
        List<String> out = new LinkedList<>();

        out.add("Sandwich: " + getName());
        if (getSize() == null || getSize().isEmpty()) {
            out.add("Size: Not specified");
        } else {
            out.add("Size: " + getSize());
        }
        out.add(String.format("%s %s%s  - $%.2f",
                size,
                getBread(),
                isToasted() ? " Toasted" : "",
                basePrice));

        if (getPremiumToppings() != null && !getPremiumToppings().isEmpty()) {
            String prem = getPremiumToppings().stream()
                    .map(t -> t.getName() + (t.isExtra() ? "(+)" : ""))
                    .collect(Collectors.joining(", "));
            out.add("Premium: " + prem);
        }

        if (getRegularToppings() != null && !getRegularToppings().isEmpty()) {
            String reg = getRegularToppings().stream()
                    .map(RegularTopping::getName)
                    .collect(Collectors.joining(", "));
            out.add("Regular: " + reg);
        }


        if (getSauces() != null && !getSauces().isEmpty()) {
            String sau = getSauces().stream()
                    .map(Sauce::getName)
                    .collect(Collectors.joining(", "));
            out.add("Sauces: " + sau);
        }

        out.add(String.format("Total: $%.2f", getTotalPrice()));

        return out;
    }

    public void addPremiumTopping(PremiumTopping topping) {
        if (getPremiumToppings() == null)
            setPremiumToppings(new LinkedList<>());
        getPremiumToppings().add(topping);
    }

    public double getTotalPrice() {
        return getPremiumToppings().stream()
                .mapToDouble(PremiumTopping::getTotalPrice)
                .sum()
                + getRegularToppings().stream()
                .mapToDouble(RegularTopping::getBasePrice)
                .sum()
                + getBasePrice();
    }

    protected double getPremiumToppingsTotalPrice() {
        return getPremiumToppings().stream()
                .mapToDouble(t -> t.getBasePrice() + t.getExtraPrice())
                .sum();
    }

    public void addRegularTopping(RegularTopping selectedTopping) {
        getRegularToppings().add(selectedTopping);
    }

    public void addSauce(Sauce sauce) {
        getSauces().add(sauce);
    }
}
