package com.pluralsight.ui.forms.dto;

import com.pluralsight.model.sandwich.RegularTopping;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SandwichDto {
    private Double basePrice;
    private String sizeName;
    private String bread;
    private List<PremiumToppingDto> premiumToppings = new LinkedList<>();
    private List<RegularTopping> regularToppings = new LinkedList<>();
    private List<String> sauces = new LinkedList<>();

    public void addPremiumTopping(PremiumToppingDto topping) {
        if (premiumToppings == null)
            premiumToppings = new LinkedList<>();
        premiumToppings.add(topping);
    }

    public List<String> getRepresentation() {
        List<String> representation = new LinkedList<>();
        representation.add("Sandwich Size: " + sizeName);
        representation.add("Bread: " + bread);
        representation.add("");
        representation.add("Base Price: $" + basePrice);
        representation.add("");

        if (premiumToppings != null && !premiumToppings.isEmpty()) {
            representation.add("Premium Toppings:");
            Map<String, List<PremiumToppingDto>> categorizedToppings = premiumToppings.stream()
                    .collect(Collectors.groupingBy(PremiumToppingDto::getCategory));
            for (var entry : categorizedToppings.entrySet()) {
                representation.add(entry.getKey() + ":");
                for (PremiumToppingDto topping : entry.getValue()) {
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
                representation.add(topping.getName());
            }
        }

        if (sauces != null && !sauces.isEmpty()) {
            representation.add("Sauces:");
            representation.addAll(sauces);
        }

        representation.add("");
        representation.add("Total Price: $" + getTotalPrice());
        representation.add("");

        return representation;
    }


    private Double getTotalPrice() {
        return premiumToppings.stream()
                .mapToDouble(PremiumToppingDto::getTotalPrice)
                .sum()
                + basePrice;
    }

    private Double getPremiumToppingsTotalPrice() {
        return premiumToppings.stream()
                .mapToDouble(t -> t.getBasePrice() + t.getExtraPrice())
                .sum();
    }
}

