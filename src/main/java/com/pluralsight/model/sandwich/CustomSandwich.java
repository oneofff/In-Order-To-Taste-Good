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



    public List<String> getRepresentation() {
        List<String> representation = new LinkedList<>();
        representation.add("Sandwich Size: " + getSize() + " - $" + getBasePrice());
        representation.add("Bread: " + getBread() + (isToasted() ? " Toasted" : ""));

        if (getPremiumToppings() != null && !getPremiumToppings().isEmpty()) {
            representation.add("Premium Toppings:");
            Map<String, List<PremiumTopping>> categorizedToppings = getPremiumToppings().stream()
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


        if (getRegularToppings() != null && !getRegularToppings().isEmpty()) {
            representation.add("Regular Toppings:");
            for (RegularTopping topping : getRegularToppings()) {
                representation.add("- " + topping.getName());
            }
        }

        representation.add("");

        if (getSauces() != null && !getSauces().isEmpty()) {
            representation.add("Sauces:");
            for (var sauce : getSauces()) {
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

}

