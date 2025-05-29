package com.pluralsight.model.sandwich;

import com.pluralsight.model.order.OrderItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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

    public List<String> getRepresentation() {
        List<String> representation = new LinkedList<>();
        representation.add("Sandwich: " + getName());
        representation.add("Sandwich Size: " + getSize() + " - $" + getBasePrice());
        representation.add("Bread: " + getBread() + (isToasted() ? " Toasted" : ""));

        if (getAllPremiumToppings() != null && !getAllPremiumToppings().isEmpty()) {
            representation.add("");
            representation.add("Premium Toppings:");
            Map<String, List<PremiumTopping>> categorizedToppings = getAllPremiumToppings().stream()
                    .collect(Collectors.groupingBy(PremiumTopping::getCategory));
            for (var entry : categorizedToppings.entrySet()) {
                representation.add(" ".repeat(4) + entry.getKey() + ":");
                for (PremiumTopping topping : entry.getValue()) {
                    representation.add(getItemRepresentation(topping.getRepresentation()));
                }
            }
        }

        if (getPremiumToppings() != null && !getPremiumToppings().isEmpty()) {
            representation.add("");
            representation.add("Total Premium Toppings Price: $" + getPremiumToppingsTotalPrice());
            representation.add("");
        }

        if (getAllRegularToppings() != null && !getAllRegularToppings().isEmpty()) {
            representation.add("Regular Toppings:");
            for (RegularTopping topping : getAllRegularToppings()) {
                representation.add(getItemRepresentation(topping.getRepresentation()));
            }
        }


        if (getAllSauces() != null && !getAllSauces().isEmpty()) {
            representation.add("Sauces:");
            for (var sauce : getAllSauces()) {
                representation.add(getItemRepresentation(sauce.getName()));
            }

        }

        representation.add("");
        representation.add(String.format("Total Price: $%.2f%s",
                getBasePrice(),
                getPremiumToppingsTotalPrice() == 0.0 ? "" :
                        String.format(" + $%.2f  = %.2f",
                                getPremiumToppingsTotalPrice(),
                                getTotalPrice())));

        return representation;
    }

    private static String getItemRepresentation(String string) {
        return " ".repeat(4) + "- " + string;
    }

    @Override
    public List<String> getShortRepresentation() {
        List<String> out = new LinkedList<>();

        out.add("Sandwich: " + getName() + " - " + getSize());


        out.add(String.format("Bread: %s, %s",
                getBread(),
                isToasted() ? " Toasted" : ""));


        if (getAllPremiumToppings() != null && !getAllPremiumToppings().isEmpty()) {
            String prem = getAllPremiumToppings().stream()
                    .map(t -> t.getName() + (t.isExtra() ? "(+)" : ""))
                    .collect(Collectors.joining(", "));
            out.add("Premium: " + prem);
        }

        if (getAllRegularToppings() != null && !getAllRegularToppings().isEmpty()) {
            String reg = getAllRegularToppings().stream()
                    .map(RegularTopping::getName)
                    .collect(Collectors.joining(", "));
            out.add("Regular: " + reg);
        }


        if (getAllSauces() != null && !getAllSauces().isEmpty()) {
            String sau = getAllSauces().stream()
                    .map(Sauce::getName)
                    .collect(Collectors.joining(", "));
            out.add("Sauces: " + sau);
        }

        out.add(String.format("Price: $%.2f", getTotalPrice()));

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

    protected List<PremiumTopping> getAllPremiumToppings() {
        return getPremiumToppings();
    }

    protected List<RegularTopping> getAllRegularToppings() {
        return getRegularToppings();
    }

    protected List<Sauce> getAllSauces() {
        return getSauces();
    }

    public List<Topping> getAllToppings() {
        List<Topping> allToppings = new LinkedList<>();
        allToppings.addAll(getAllPremiumToppings());
        allToppings.addAll(getAllRegularToppings());
        return allToppings;
    }

    public void removeTopping(Topping selectedTopping) {
        if (selectedTopping instanceof PremiumTopping premiumTopping) {
            getPremiumToppings().removeIf(t -> t.getName().equals(premiumTopping.getName()));
        } else if (selectedTopping instanceof RegularTopping regularTopping) {
            getRegularToppings().removeIf(t -> t.getName().equals(regularTopping.getName()));
        }
    }
}
