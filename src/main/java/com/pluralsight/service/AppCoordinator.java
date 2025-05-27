package com.pluralsight.service;

import com.pluralsight.model.order.Order;
import com.pluralsight.ui.entryexit.SeeYouScreen;
import com.pluralsight.ui.entryexit.WelcomeScreen;
import com.pluralsight.ui.menu.AddSandwichScreenMenu;
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
            performAction(option, HomeScreenMenu.MenuOption.class, null);
        }
    }

    public static void orderScreenFlow() {
        Order order = new Order();
        while (true) {
            OrderScreenMenu.print();
            int option = ScreenUtils.askForMenuOptionsInput(OrderScreenMenu.amountOfOptions());
            performAction(option, OrderScreenMenu.MenuOption.class, order);
        }
    }

    public static void addSandwichFlow(Order order) {
        while (true) {
            AddSandwichScreenMenu.print();
            int option = ScreenUtils.askForMenuOptionsInput(AddSandwichScreenMenu.amountOfOptions());
            performAction(option, AddSandwichScreenMenu.MenuOption.class, order);
        }
    }

    private static <M, T extends Enum<T> & MenuEntry<M>> void performAction(int option, Class<T> menu,
                                                                            M context) {
        T menuOption = getMenuOption(option, menu);
        ScreenUtils.cls();
        if (menuOption == null) {
            System.out.println("Invalid option. Please try again.");
            return;
        }
        menuOption.getAction().accept(context);
    }


    private static <M, T extends Enum<T> & MenuEntry<M>> T getMenuOption(int option, Class<T> menu) {
        return Arrays.stream(menu.getEnumConstants())
                .filter(entry -> entry.getValue() == option)
                .findFirst()
                .orElse(null);
    }

    public static void exit() {
        SeeYouScreen.print();
        System.exit(0);
    }
}
