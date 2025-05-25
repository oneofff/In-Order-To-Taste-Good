package com.pluralsight.service;

import com.pluralsight.ui.entryexit.SeeYouScreen;
import com.pluralsight.ui.entryexit.WelcomeScreen;
import com.pluralsight.ui.menu.AddSandwichMenu;
import com.pluralsight.ui.menu.HomeScreenMenu;
import com.pluralsight.ui.menu.MenuEntry;
import com.pluralsight.ui.menu.OrderScreenMenu;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.Arrays;

public class AppCoordinator {

    public static void start() {
        WelcomeScreen.print();
        homeScreenFlow();
    }

    public static void homeScreenFlow() {
        while (true) {
            HomeScreenMenu.print();
            int option = ScreenUtils.askForMenuOptionsInput(HomeScreenMenu.amountOfOptions());
            performAction(option, HomeScreenMenu.MenuOption.class);
        }
    }

    public static void orderScreenFlow() {
        while (true) {
            OrderScreenMenu.print();
            int option = ScreenUtils.askForMenuOptionsInput(OrderScreenMenu.amountOfOptions());
            performAction(option, OrderScreenMenu.MenuOption.class);
        }
    }

    public static void addSandwichFlow() {
        while (true) {
            AddSandwichMenu.print();
            int option = ScreenUtils.askForMenuOptionsInput(AddSandwichMenu.amountOfOptions());
            performAction(option, AddSandwichMenu.MenuOption.class);
        }
    }

    public static <T extends Enum<T> & MenuEntry> void performAction(int option, Class<T> menu) {
        T menuOption = Arrays.stream(menu.getEnumConstants())
                .filter(entry -> entry.getValue() == option)
                .findFirst()
                .orElse(null);
        if (menuOption != null) {
            ScreenUtils.cls();
            menuOption.getAction().run();
        } else {
            System.out.println("Invalid option. Please try again.");
        }
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

    public static void exit() {
        SeeYouScreen.print();
        System.exit(0);
    }


}
