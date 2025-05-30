package com.pluralsight.utils.console;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public final class ConsoleStringReader {

    public static Scanner scanner = new Scanner(System.in);

    public static int getIntInRangeWithMargin(int rangeStart, int rangeEnd) {
        int value;
        while (true) {
            try {
                ScreenUtils.printWithMargins("");
                value = scanner.nextInt();
                scanner.nextLine();
                if (value < rangeStart || value > rangeEnd) {
                    ScreenUtils.printlnWithMargins("Invalid input. Value should be between " + rangeStart + " and " + rangeEnd + ".");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                ScreenUtils.printlnWithMargins("Invalid input. Please enter a numeric value between " + rangeStart + " and " + rangeEnd + ".");
                scanner.nextLine();
            }
        }
    }

    public static <T> int getIntInRangeOfCollection(List<T> collection, boolean isZeroExitOption) {
        int rangeStart = isZeroExitOption ? 0 : 1;
        return getIntInRangeWithMargin(rangeStart, collection.size());
    }

    public static String getStringWithMargin() {
        ScreenUtils.printWithMargins("");
        return scanner.nextLine();
    }
}
