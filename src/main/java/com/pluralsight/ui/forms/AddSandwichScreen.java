package com.pluralsight.ui.forms;

import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

public class AddSandwichScreen {

    public static void addSandwich() {
        ScreenUtils.printlnWithMargins("Select your bread");
        //TODO display bread options
        int breadType = ConsoleStringReader.getIntInRangeWithMargin(1, 5);


        ScreenUtils.printlnWithMargins("Sandwich size: ");
        //TODO display sandwich size
        int size = ConsoleStringReader.getIntInRangeWithMargin(1, 5);
        ScreenUtils.printlnWithMargins("Toppings: ");
        ScreenUtils.printlnWithMargins("Do you want extra meat: ");
        boolean extraMeat = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.printlnWithMargins("Do you want extra cheese: ");
        boolean extraCheese = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.printlnWithMargins("Do you want extra toppings: ");
        boolean extraToppings = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.printlnWithMargins("Select sauces: ");
        int sauces = ConsoleStringReader.getIntInRangeWithMargin(1, 5);
        ScreenUtils.printlnWithMargins("Would you like the sandwich toasted? ");
        boolean toasted = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        //TODO return SANDWICH
    }
}
