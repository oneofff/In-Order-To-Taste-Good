package com.pluralsight.ui.forms;

import com.pluralsight.model.order.Order;
import com.pluralsight.model.sandwich.SignatureSandwich;
import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class AddSignatureSandwichScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();

    public void addSignatureSandwich(Order order) {
        List<SignatureSandwich> signatureSandwiches = menuRepository.getSignatureSandwiches();

        if (signatureSandwiches == null || signatureSandwiches.isEmpty()) {
            ScreenUtils.cls();
            ScreenUtils.printOnCenterOfTheScreen("Sorry, no signature sandwiches are available at the moment.");
            ScreenUtils.waitTillPressEnter();
            return;
        }

        ScreenUtils.printOnCenterOfTheScreen("Please select a Signature Sandwich:");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                signatureSandwiches,
                (sandwich) -> String.format("%s - $%.2f", sandwich.getName(), sandwich.getBasePrice()),
                "Back to Add Sandwich Menu"
        ));

        int choice = ConsoleStringReader.getIntInRangeOfCollection(signatureSandwiches, true);
        ScreenUtils.cls();

        if (choice == 0) {
            return;
        }

        SignatureSandwich selectedSandwich = signatureSandwiches.get(choice - 1);

        if (confirmAddToOrder(selectedSandwich)) { //
            order.addItem(selectedSandwich); //
            ScreenUtils.printOnCenterOfTheScreen("Signature sandwich '" + selectedSandwich.getName() + "' added to your order.");
            ScreenUtils.printBox(order.getOrderDetails()); //
        } else {
            ScreenUtils.printOnCenterOfTheScreen("Signature sandwich '" + selectedSandwich.getName() + "' was not added to your order.");
            List<String> sandwichDetails = new ArrayList<>();
            sandwichDetails.add("Sandwich: " + selectedSandwich.getName());
            sandwichDetails.add("Bread: " + selectedSandwich.getBread()); // Assumes bread is set for signature sandwiches
            sandwichDetails.add("Toasted: " + (selectedSandwich.isToasted() ? "Yes" : "No"));
            sandwichDetails.add("Ingredients: ");
            if (selectedSandwich.getIngredients() != null && !selectedSandwich.getIngredients().isEmpty()) {
                for (String ingredient : selectedSandwich.getIngredients()) {
                    sandwichDetails.add("- " + ingredient);
                }
            } else {
                sandwichDetails.add("- (Not specified)");
            }
            sandwichDetails.add(String.format("Price: $%.2f", selectedSandwich.getBasePrice())); //
            ScreenUtils.printBox(sandwichDetails);

        }
        ScreenUtils.waitTillPressEnter(); //
    }

    private boolean confirmAddToOrder(SignatureSandwich sandwich) { //
        ScreenUtils.printOnCenterOfTheScreen("You selected: " + sandwich.getName());
        List<String> sandwichDetails = new ArrayList<>();
        sandwichDetails.add("Details for: " + sandwich.getName());
        sandwichDetails.add("------------------------------");
        sandwichDetails.add("Bread: " + (sandwich.getBread() != null ? sandwich.getBread() : "As specified")); //
        sandwichDetails.add("Toasted: " + (sandwich.isToasted() ? "Yes" : "No")); //

        sandwichDetails.add("Ingredients:");
        if (sandwich.getIngredients() != null && !sandwich.getIngredients().isEmpty()) { //
            for (String ingredient : sandwich.getIngredients()) {
                sandwichDetails.add("  - " + ingredient);
            }
        } else {
            sandwichDetails.add("  - (Standard signature ingredients)");
        }
        // In SignatureSandwich.java, getTotalPrice() returns 0. Using getBasePrice() from menu.json.
        sandwichDetails.add(String.format("Price: $%.2f", sandwich.getBasePrice())); //
        sandwichDetails.add("------------------------------");

        ScreenUtils.printBox(sandwichDetails);
        ScreenUtils.printOnCenterOfTheScreen("Add this sandwich to your order?");
        ScreenUtils.printOnCenterOfTheScreen("1. Yes");
        ScreenUtils.printOnCenterOfTheScreen("2. No");

        int confirmationChoice = ConsoleStringReader.getIntInRangeWithMargin(1, 2); //
        ScreenUtils.cls();
        return confirmationChoice == 1;
    }
}