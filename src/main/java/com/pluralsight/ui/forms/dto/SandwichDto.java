package com.pluralsight.ui.forms.dto;

import com.pluralsight.model.sandwich.RegularTopping;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
public class SandwichDto {
    private Double basePrice;
    private String sizeName;
    private String bread;
    private List<PremiumToppingDto> premiumToppingsMenus;
    private List<RegularTopping> regularToppings;
    private List<String> sauces;

    public void addPremiumTopping(PremiumToppingDto topping) {
        if (premiumToppingsMenus == null)
            premiumToppingsMenus = new LinkedList<>();
        premiumToppingsMenus.add(topping);
    }

    public List<String> getRepresentation() {
        List<String> representation = new LinkedList<>();
        representation.add("Sandwich Size: " + sizeName);
        representation.add("Bread: " + bread);
        representation.add("");
        representation.add("Base Price: $" + basePrice);
        representation.add("");

        if (premiumToppingsMenus != null && !premiumToppingsMenus.isEmpty()) {
            representation.add("Premium Toppings:");
            for (PremiumToppingDto topping : premiumToppingsMenus) {
                representation.add(topping.getRepresentation());
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
        return premiumToppingsMenus.stream()
                .mapToDouble(PremiumToppingDto::getTotalPrice)
                .sum();
    }

    private Double getPremiumToppingsTotalPrice() {
        return premiumToppingsMenus.stream()
                .mapToDouble(PremiumToppingDto::getBasePrice)
                .sum();
    }
}

