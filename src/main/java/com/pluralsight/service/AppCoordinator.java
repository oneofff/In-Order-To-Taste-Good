package com.pluralsight.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pluralsight.model.menu.Menu;
import com.pluralsight.ui.entryexit.SeeYouScreen;
import com.pluralsight.ui.entryexit.WelcomeScreen;
import com.pluralsight.ui.menu.HomeScreenMenu;
import com.pluralsight.ui.menu.MenuEntry;
import com.pluralsight.ui.menu.OrderScreenMenu;
import com.pluralsight.utils.console.ScreenUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class AppCoordinator {

    public static void start() {
        Menu menu = loadMenu("data/menu.json");
        System.out.println(menu.getBreadOptions());
        System.out.println(menu.getSandwichSizePrices());
        System.out.println(menu.getToppings());
        System.out.println(menu.getSauces());
        System.out.println(menu.getDrinkOptions());
        System.out.println(menu.getChipsOptions());

        WelcomeScreen.print();
        homeScreenFlow();
    }

    public static Menu loadMenu(String jsonPath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(jsonPath), Menu.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load menu data", e);
        }
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
