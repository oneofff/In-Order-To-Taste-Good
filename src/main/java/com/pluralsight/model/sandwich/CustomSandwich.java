package com.pluralsight.model.sandwich;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CustomSandwich extends Sandwich {
    private Double basePrice;
    private String sizeName;
    private String bread;
    private boolean isToasted;
    private List<PremiumTopping> premiumToppings = new LinkedList<>();
    private List<RegularTopping> regularToppings = new LinkedList<>();
    private List<Sauce> sauces = new LinkedList<>();

    public void addPremiumTopping(PremiumTopping topping) {
        if (premiumToppings == null)
            premiumToppings = new LinkedList<>();
        premiumToppings.add(topping);
    }

    public List<String> getRepresentation() {
        List<String> representation = new LinkedList<>();
        representation.add("Sandwich Size: " + sizeName + " - $" + basePrice);
        representation.add("Bread: " + bread + (isToasted ? " Toasted" : ""));

        if (premiumToppings != null && !premiumToppings.isEmpty()) {
            representation.add("Premium Toppings:");
            Map<String, List<PremiumTopping>> categorizedToppings = premiumToppings.stream()
                    .collect(Collectors.groupingBy(PremiumTopping::getCategory));
            for (var entry : categorizedToppings.entrySet()) {
                representation.add(entry.getKey() + ":");
                for (PremiumTopping topping : entry.getValue()) {
                    representation.add(topping.getRepresentation());
                }
            }
        }
        representation.add("");
        representation.add("Premium Toppings Total Price: $" + getPremiumToppingsTotalPrice());
        representation.add("");


        if (regularToppings != null && !regularToppings.isEmpty()) {
            representation.add("Regular Toppings:");
            for (RegularTopping topping : regularToppings) {
                representation.add("- " + topping.getName());
            }
        }

        representation.add("");

        if (sauces != null && !sauces.isEmpty()) {
            representation.add("Sauces:");
            for (var sauce : sauces) {
                representation.add("- " + sauce.getName());
            }

        }

        representation.add("");
        representation.add(String.format("Total Price: $%.2f + $%.2f = $%.2f",
                getBasePrice(),
                getPremiumToppingsTotalPrice(),
                getTotalPrice()));

        return representation;
    }


    public double getTotalPrice() {
        return premiumToppings.stream()
                .mapToDouble(PremiumTopping::getTotalPrice)
                .sum()
                + regularToppings.stream()
                .mapToDouble(RegularTopping::getPrice)
                .sum()
                + basePrice;
    }

    private double getPremiumToppingsTotalPrice() {
        return premiumToppings.stream()
                .mapToDouble(t -> t.getBasePrice() + t.getExtraPrice())
                .sum();
    }

    public void addRegularTopping(RegularTopping selectedTopping) {
        regularToppings.add(selectedTopping);
    }

    public void addSauce(Sauce sauce) {
        sauces.add(sauce);
    }
}

