package com.pluralsight.ui.entryexit;

import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class SeeYouScreen {
    public static void print() {

        try {
            ScreenUtils.printBox(List.of("See you next time!",
                    "Thank you for your order!",
                    "Goodbye!"));
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
