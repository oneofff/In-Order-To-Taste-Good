package com.pluralsight.utils.console;


import java.util.LinkedList;
import java.util.List;

public final class ScreenUtils {
    private static final int SCREEN_HEIGHT_LINES = 20;
    private static final int DEFAULT_BORDER_LENGTH = 52;
    private static final int INNER_WIDTH = DEFAULT_BORDER_LENGTH - 2;
    private static final int SCREEN_WIDTH = 167;

    public static void printBox(List<String> lines, List<String> additionalLinesToTheRight) {
        String border = buildBorder();
        printlnWithMargins(border);
        int contentLength = lines.size();

        int amountOfEmptyLines = (SCREEN_HEIGHT_LINES - contentLength - 2) / 2;
        printEmptyLinesOnTop(amountOfEmptyLines);

        int blockPad = leftPadForBlock(lines);

        int rows = Math.max(lines.size(), additionalLinesToTheRight.size());
        for (int i = 0; i < rows; i++) {

            String inside = (i < lines.size())
                    ? "|" + renderRow(lines.get(i), blockPad, INNER_WIDTH) + "|"
                    : "|" + " ".repeat(INNER_WIDTH) + "|";

            String extra = (i < additionalLinesToTheRight.size())
                    ? " ".repeat(5) + additionalLinesToTheRight.get(i)
                    : "";

            printlnWithMargins(inside + extra);
        }


        printEmptyLinesOnBottom(amountOfEmptyLines);

        printlnWithMargins(border);
        evenFromTheBottom(contentLength + 2 + amountOfEmptyLines);
    }

    private static int leftPadForBlock(List<String> lines) {
        int max = lines.stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
        max = Math.min(max, INNER_WIDTH);
        return (INNER_WIDTH - max) / 2;
    }

    private static String renderRow(String txt, int pad, int width) {
        if (txt.length() > width - pad)
            txt = txt.substring(0, width - pad);
        int trailing = width - pad - txt.length();
        return " ".repeat(pad) + txt + " ".repeat(trailing);
    }

    public static void printBox(List<String> lines) {
        printBox(lines, new LinkedList<>());
    }


    private static String buildBorder() {
        return "!" +
                "@".repeat(DEFAULT_BORDER_LENGTH - 2) +
                "!";
    }

    private static void printEmptyLinesOnBottom(int amountOfEmptyLines) {
        int printOnBottom = amountOfEmptyLines / 2;
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
        int startPosition = (SCREEN_WIDTH - message.length()) / 2;
        System.out.println(" ".repeat(startPosition) + message);
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
