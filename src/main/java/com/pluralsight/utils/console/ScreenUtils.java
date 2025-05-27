package com.pluralsight.utils.console;


import java.util.List;

public final class ScreenUtils {
    private static final int SCREEN_HEIGHT_LINES = 20;
    private static final int DEFAULT_BORDER_LENGTH = 52;
    private static final int INNER_WIDTH = DEFAULT_BORDER_LENGTH - 2;

    public static void printBox(List<String> lines, String... additionalLines) {
        String border = buildBorder();
        printlnWithMargins(border);
        int contentLength = lines.size() + additionalLines.length + 2;

        int amountOfEmptyLines = (SCREEN_HEIGHT_LINES - contentLength - 2) / 2;
        printEmptyLinesOnTop(amountOfEmptyLines);


        for (String line : lines) {
            printlnWithMargins("|" + center(line, INNER_WIDTH) + "|");
        }

        for (String additionalLine : additionalLines) {
            printlnWithMargins("|" + center(additionalLine, INNER_WIDTH) + "|");
        }

        printEmptyLinesOnBottom(amountOfEmptyLines);

        printlnWithMargins(border);
        evenFromTheBottom(contentLength + 2 + amountOfEmptyLines);
    }


    private static String buildBorder() {
        return "!" +
                "@".repeat(DEFAULT_BORDER_LENGTH - 2) +
                "!";
    }

    private static void printEmptyLinesOnBottom(int amountOfEmptyLines) {
        int printOnBottom = amountOfEmptyLines / 2;
        // print empty lines on bottom in the box
        for (int i = 0; i < printOnBottom; i++) {
            printlnWithMargins("|" + " ".repeat(INNER_WIDTH) + "|");
        }
    }

    private static void printEmptyLinesOnTop(int amountOfEmptyLines) {
        int printOnTop = amountOfEmptyLines / 2;

        for (int i = 0; i < printOnTop; i++) {
            printlnWithMargins("|" + " ".repeat(INNER_WIDTH) + "|");
        }
    }

    private static String center(String text, int width) {
        if (text.length() >= width) {
            return text.substring(0, width);
        }
        int padding = width - text.length();
        int left = padding / 2;
        int right = padding - left;
        return " ".repeat(left) + text + " ".repeat(right);
    }


    public static void waitTillPressEnter() {
        printOnCenterOfTheScreen("Press Enter to continue...");
        while (true) {
            if (ConsoleStringReader.getStringWithMargin().isEmpty()) {
                break;
            }
        }
    }

    public static void printlnWithMargins(String message) {
        System.out.println("\t".repeat(15) + message);
    }

    public static void printfWithMargins(String message, Object... args) {
        System.out.printf("\t".repeat(15) + message, args);
    }

    public static void printWithMargins(String message) {
        System.out.print("\t".repeat(15) + message);
    }

    public static void evenFromTheBottom(int linesOnTheScreen) {
        int linesToPrint = (SCREEN_HEIGHT_LINES - linesOnTheScreen) / 2;
        for (int i = 0; i < linesToPrint; i++) {
            System.out.println();
        }
    }

    public static void printOnCenterOfTheScreen(String message) {
        System.out.println("\t".repeat(18) + message);
    }

    public static void cls() {
        for (int i = 0; i < 25; i++) {
            System.out.println();
        }
    }

    public static int askForMenuOptionsInput(int amountOfOptions) {
        ScreenUtils.printOnCenterOfTheScreen("Please select an option: ");
        return ConsoleStringReader.getIntInRangeWithMargin(0, amountOfOptions - 1);
    }
}
