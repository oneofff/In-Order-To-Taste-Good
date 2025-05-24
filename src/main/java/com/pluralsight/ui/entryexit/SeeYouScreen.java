package com.pluralsight.ui.entryexit;

import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class SeeYouScreen {
    public static void print() {

        try {
            ScreenUtils.printBox(List.of(
                    "Thank you for your order!",
                    "See you next time!",
                    "Goodbye!"));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
