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

        addIfNotEmpty(representation, getMainSandwichInfo());

        addIfNotEmpty(representation, addPremiumToppingsInfo());

        addIfNotEmpty(representation, getRegularToppingsInfo());

        addIfNotEmpty(representation, getSaucesInfo());

        addIfNotEmpty(representation, getPriceInfo());

        return representation;
    }

    private void addIfNotEmpty(List<String> representation, List<String> strings) {
        if (strings != null && !strings.isEmpty()) {
            representation.addAll(strings);
        }
    }

    private void addIfNotEmpty(List<String> representation, String string) {
        if (string != null && !string.isEmpty()) {
            representation.add(string);
        }
    }

    private String getPriceInfo() {
        double basePrice = getBasePrice();
        double toppingsPrice = getPremiumToppingsTotalPrice();
        double totalPrice = getTotalPrice();

        return (toppingsPrice == 0.0)
                ? String.format("Total Price: $%.2f", basePrice)
                : String.format("Total Price: $%.2f + $%.2f = $%.2f",
                basePrice, toppingsPrice, totalPrice);
    }

    private List<String> getSaucesInfo() {
        if (getAllSauces() == null || getAllSauces().isEmpty())
            return List.of();

        List<String> saucesInfo = new LinkedList<>();

        saucesInfo.add("Sauces:");
        for (var sauce : getAllSauces()) {
            saucesInfo.add(getItemRepresentation(sauce.getName()));
        }
        saucesInfo.add("");
        return saucesInfo;
    }

    private List<String> getRegularToppingsInfo() {
        if (getAllRegularToppings() == null || getAllRegularToppings().isEmpty())
            return List.of();

        List<String> regularToppingsInfo = new LinkedList<>();

        regularToppingsInfo.add("Regular Toppings:");
        for (RegularTopping topping : getAllRegularToppings()) {
            regularToppingsInfo.add(getItemRepresentation(topping.getRepresentation()));
        }
        regularToppingsInfo.add("");
        return regularToppingsInfo;
    }

    private List<String> addPremiumToppingsInfo() {
        if (getAllPremiumToppings() == null || getAllPremiumToppings().isEmpty())
            return List.of();

        List<String> premiumToppingsInfo = new LinkedList<>();

        premiumToppingsInfo.add("Premium Toppings:");

        Map<String, List<PremiumTopping>> categorizedToppings = getCategorizedToppings();
        premiumToppingsInfo.addAll(buildPremiumToppingsLines(categorizedToppings));

        premiumToppingsInfo.add("");
        premiumToppingsInfo.add("Total Premium Toppings Price: $" + getPremiumToppingsTotalPrice());
        premiumToppingsInfo.add("");

        return premiumToppingsInfo;
    }

    private List<String> buildPremiumToppingsLines(Map<String, List<PremiumTopping>> categorizedToppings) {
        List<String> premiumToppingsInfo = new LinkedList<>();
        for (var entry : categorizedToppings.entrySet()) {
            premiumToppingsInfo.add(" ".repeat(4) + entry.getKey() + ":");
            for (PremiumTopping topping : entry.getValue()) {
                premiumToppingsInfo.add(getItemRepresentation(topping.getRepresentation()));
            }
        }
        return premiumToppingsInfo;
    }

    private Map<String, List<PremiumTopping>> getCategorizedToppings() {
        return getAllPremiumToppings().stream()
                .collect(Collectors.groupingBy(PremiumTopping::getCategory));
    }

    private List<String> getMainSandwichInfo() {
        return List.of("Sandwich: " + getName(),
                "Sandwich Size: " + getSize() + " - $" + getBasePrice(),
                "Bread: " + getBread() + (isToasted() ? " Toasted" : ""),
                "");
    }

    private static String getItemRepresentation(String string) {
        return " ".repeat(4) + "- " + string;
    }

    @Override
    public List<String> getShortRepresentation() {
        List<String> shortRepresentation = new LinkedList<>();

        addIfNotEmpty(shortRepresentation, getShortMainSandwichInfo());

        addIfNotEmpty(shortRepresentation, getShortBreadInfo());

        addIfNotEmpty(shortRepresentation, getShortPremiumToppingsInfo());

        addIfNotEmpty(shortRepresentation, getShortRegularToppingsInfo());

        addIfNotEmpty(shortRepresentation, getShortSauceInfo());

        addIfNotEmpty(shortRepresentation, getShortPriceInfo());

        return shortRepresentation;
    }

    private String getShortPriceInfo() {
        return String.format("Price: $%.2f", getTotalPrice());
    }

    private String getShortSauceInfo() {
        if (getAllSauces() == null || getAllSauces().isEmpty()) return "";
        return "Sauces: " + getAllSauces().stream()
                .map(Sauce::getName)
                .collect(Collectors.joining(", "));
    }

    private String getShortRegularToppingsInfo() {
        if (getAllRegularToppings() == null || getAllRegularToppings().isEmpty()) return "";

        return "Regular: " + getAllRegularToppings().stream()
                .map(RegularTopping::getName)
                .collect(Collectors.joining(", "));
    }

    private String getShortPremiumToppingsInfo() {
        if (getAllPremiumToppings() == null || getAllPremiumToppings().isEmpty()) return "";

        String shortPremiumToppingsInfo = getAllPremiumToppings().stream()
                .map(t -> t.getName() + (t.isExtra() ? "(+)" : ""))
                .collect(Collectors.joining(", "));
        return "Premium: " + shortPremiumToppingsInfo;
    }

    private String getShortBreadInfo() {
        return String.format("Bread: %s, %s",
                getBread(),
                isToasted() ? " Toasted" : "");
    }

    private String getShortMainSandwichInfo() {
        return "Sandwich: " + getName() + " - " + getSize();
    }

    public void addPremiumTopping(PremiumTopping topping) {
        if (this.premiumToppings == null)
            setPremiumToppings(new LinkedList<>());
        this.premiumToppings.add(topping);
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

    public void removeTopping(Topping selectedTopping) {
        if (selectedTopping instanceof PremiumTopping premiumTopping) {
            getPremiumToppings().removeIf(t -> t.getName().equals(premiumTopping.getName()));
        } else if (selectedTopping instanceof RegularTopping regularTopping) {
            getRegularToppings().removeIf(t -> t.getName().equals(regularTopping.getName()));
        }
    }

    protected abstract List<PremiumTopping> getAllPremiumToppings();

    protected abstract List<RegularTopping> getAllRegularToppings();

    protected abstract List<Sauce> getAllSauces();

    public List<Topping> getAllToppings() {
        List<Topping> allToppings = new LinkedList<>();
        allToppings.addAll(getAllPremiumToppings());
        allToppings.addAll(getAllRegularToppings());
        return allToppings;
    }
}
