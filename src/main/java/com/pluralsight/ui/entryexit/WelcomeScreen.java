package com.pluralsight.ui.entryexit;

import com.pluralsight.utils.console.ScreenUtils;

public class WelcomeScreen {

    public static void print() {
        ScreenUtils.cls();
        ScreenUtils.printlnWithMargins("!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@!");
        ScreenUtils.printlnWithMargins("!                                                 !");
        ScreenUtils.printlnWithMargins("!                                                 !");
        ScreenUtils.printlnWithMargins("!                                                 !");
        ScreenUtils.printlnWithMargins("!                                                 !");
        ScreenUtils.printlnWithMargins("!             In Order To Taste Good              !");
        ScreenUtils.printlnWithMargins("!                 Sandwich shop                   !");
        ScreenUtils.printlnWithMargins("!                                                 !");
        ScreenUtils.printlnWithMargins("!                                                 !");
        ScreenUtils.printlnWithMargins("!                                                 !");
        ScreenUtils.printlnWithMargins("!                                                 !");
        ScreenUtils.printlnWithMargins("!                                Stanley Karol    !");
        ScreenUtils.printlnWithMargins("!                                Capstone 2 - 2025!");
        ScreenUtils.printlnWithMargins("!                                YearUp United    !");
        ScreenUtils.printlnWithMargins("!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@!");
        ScreenUtils.evenFromTheBottom(15);
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }
}
