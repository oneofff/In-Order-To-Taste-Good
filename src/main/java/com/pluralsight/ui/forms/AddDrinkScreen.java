package com.pluralsight.ui.forms;

import com.pluralsight.utils.console.ConsoleStringReader;

public class AddDrinkScreen {
    public static void addDrink() {
        System.out.print("Choose a drink to add: ");
        //TODO display drink options
        int drinkChoice = ConsoleStringReader.getIntInRangeWithMargin(1, 3);
        System.out.println("Please select drunk size:");
        System.out.println("1. Small");
        System.out.println("2. Medium");
        System.out.println("3. Large");
        System.out.print("Enter your choice (1-3): ");
        int sizeChoice = ConsoleStringReader.getIntInRangeWithMargin(1, 3);


        System.out.println("Drink added successfully!");
    }
}
