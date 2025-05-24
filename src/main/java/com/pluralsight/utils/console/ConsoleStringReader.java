package com.pluralsight.utils.console;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public final class ConsoleStringReader {

    public static final Scanner scanner = new Scanner(System.in);

    public static double getPositiveDoubleWithMargin() {
        double value;
        while (true) {
            try {
                ScreenUtils.printWithMargins("");
                value = scanner.nextDouble();
                scanner.nextLine();
                if (value <= 0) {
                    ScreenUtils.printlnWithMargins("Invalid input. Value should be greater than 0.");
                    continue;
                }
                return value;
            } catch (InputMismatchException e) {
                ScreenUtils.printlnWithMargins("Invalid input. Please enter a numeric value greater than 0.");
                scanner.nextLine();
            }
        }
    }

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

    public static String getStringWithMargin() {
        ScreenUtils.printWithMargins("");
        return scanner.nextLine();
    }

    public static LocalDate getDateWithMargin() {
        LocalDate date;
        while (true) {
            try {
                ScreenUtils.printWithMargins("");
                String stringDate = scanner.nextLine();
                if (stringDate.isEmpty()) {
                    return null;
                }
                date = LocalDate.parse(stringDate);
                return date;
            } catch (Exception e) {
                ScreenUtils.printlnWithMargins("Invalid input. Please enter a valid date in the format yyyy-MM-dd.");
            }
        }
    }
}
