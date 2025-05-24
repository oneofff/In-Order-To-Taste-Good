package com.pluralsight.ui.forms;

import com.pluralsight.utils.console.ConsoleStringReader;

public class AddChipsScreen {
    public static void addChips() {
        System.out.print("Choose a chips to add: ");
        //TODO display chips options
        int chipsChoice = ConsoleStringReader.getIntInRangeWithMargin(1, 3);

        System.out.println("Chips added successfully!");
    }
}
