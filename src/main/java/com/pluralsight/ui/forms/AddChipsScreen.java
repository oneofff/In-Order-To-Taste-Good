package com.pluralsight.ui.forms;

import com.pluralsight.model.extras.Chips;
import com.pluralsight.model.order.Order;
import com.pluralsight.service.DefaultMenuService;
import com.pluralsight.service.interfaces.MenuService;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class AddChipsScreen {
    private final MenuService menuService = new DefaultMenuService();

    public void addChips(Order order) {
        List<Chips> options = menuService.getChipsOptions();
        if (isEmpty(options)) {
            showUnavailable();
            return;
        }

        Chips selected = promptSelection(options);
        boolean added = promptConfirmation(selected);

        if (added) {
            order.addItem(selected);
            displayAdded(selected, order);
        } else {
            displayCancelled(selected);
        }

        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

    private boolean isEmpty(List<Chips> options) {
        return options == null || options.isEmpty();
    }

    private void showUnavailable() {
        ScreenUtils.printOnCenterOfTheScreen("Sorry, no chips are available at the moment.");
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }

    private Chips promptSelection(List<Chips> options) {
        ScreenUtils.printOnCenterOfTheScreen("Choose chips to add:");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                options,
                c -> String.format("%s - $%.2f", c.getName(), c.getPrice())
        ));

        int choice = ConsoleStringReader.getIntInRangeOfCollection(options, false);
        Chips selected = options.get(choice - 1);
        ScreenUtils.cls();
        return selected;
    }

    private boolean promptConfirmation(Chips selected) {
        ScreenUtils.printBox(List.of(
                "Chips: " + selected.getName(),
                String.format("Price: $%.2f", selected.getPrice())
        ));
        ScreenUtils.printOnCenterOfTheScreen("");
        ScreenUtils.printOnCenterOfTheScreen("Add to order? 1=Yes, 2=No");

        int confirm = ConsoleStringReader.getIntInRangeWithMargin(1, 2);
        ScreenUtils.cls();
        return confirm == 1;
    }

    private void displayAdded(Chips selected, Order order) {
        ScreenUtils.printOnCenterOfTheScreen(
                String.format("%s chips added successfully!", selected.getName())
        );
        ScreenUtils.printBox(order.getOrderRepresentation());
    }

    private void displayCancelled(Chips selected) {
        ScreenUtils.printOnCenterOfTheScreen(
                String.format("%s chips were not added.", selected.getName())
        );
    }
}
