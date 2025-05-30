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

        List<Chips> chipsOptions = menuService.getChipsOptions();

        if (chipsOptions == null || chipsOptions.isEmpty()) {
            ScreenUtils.printOnCenterOfTheScreen(
                    "Sorry, no chips are available at the moment.");
            ScreenUtils.waitTillPressEnter();
            ScreenUtils.cls();
            return;
        }

        ScreenUtils.printOnCenterOfTheScreen("Choose chips to add:");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                chipsOptions,
                c -> String.format("%s - $%.2f", c.getName(), c.getPrice())
        ));

        int choice = ConsoleStringReader.getIntInRangeOfCollection(chipsOptions, false);
        Chips selected = chipsOptions.get(choice - 1);
        ScreenUtils.cls();

        ScreenUtils.printBox(List.of(
                "Chips: " + selected.getName(),
                String.format("Price: $%.2f", selected.getPrice())
        ));
        ScreenUtils.printOnCenterOfTheScreen("");
        ScreenUtils.printOnCenterOfTheScreen("Add to order? 1=Yes, 2=No");

        int confirm = ConsoleStringReader.getIntInRangeWithMargin(1, 2);
        ScreenUtils.cls();

        if (confirm == 1) {
            order.addItem(selected);
            ScreenUtils.printOnCenterOfTheScreen(
                    String.format("%s chips added successfully!", selected.getName()));
            ScreenUtils.printBox(order.getOrderRepresentation());
        } else {
            ScreenUtils.printOnCenterOfTheScreen(
                    String.format("%s chips were not added.", selected.getName()));
        }

        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }
}
