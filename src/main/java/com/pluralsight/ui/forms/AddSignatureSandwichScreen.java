package com.pluralsight.ui.forms;

import com.pluralsight.model.order.Order;
import com.pluralsight.model.sandwich.SignatureSandwich;
import com.pluralsight.repository.IMenuRepository;
import com.pluralsight.repository.MenuRepository;
import com.pluralsight.utils.console.CollectionFormatter;
import com.pluralsight.utils.console.ConsoleStringReader;
import com.pluralsight.utils.console.ScreenUtils;

import java.util.List;

public class AddSignatureSandwichScreen {

    private final IMenuRepository menuRepository = MenuRepository.getInstance();


    public void addSignatureSandwich(Order order) {
        List<SignatureSandwich> menu = menuRepository.getSignatureSandwiches();
        if (!menuAvailable(menu)) return;

        int choice = showMenuAndGetChoice(menu);
        if (choice == 0) return;

        SignatureSandwich picked = menu.get(choice - 1);
        ScreenUtils.cls();
        new AddModificationScreen().addModification(picked);
        processSelection(order, picked);
    }

    private boolean menuAvailable(List<SignatureSandwich> menu) {
        if (menu == null || menu.isEmpty()) {
            ScreenUtils.cls();
            ScreenUtils.printOnCenterOfTheScreen(
                    "Sorry, no signature sandwiches are available at the moment.");
            ScreenUtils.waitTillPressEnter();
            return false;
        }
        return true;
    }

    private int showMenuAndGetChoice(List<SignatureSandwich> menu) {
        ScreenUtils.printOnCenterOfTheScreen("Please select a Signature Sandwich:");
        ScreenUtils.printBox(CollectionFormatter.listToMenu(
                menu,
                s -> String.format("%s - $%.2f", s.getName(), s.getBasePrice()),
                "Back to Add Sandwich Menu"
        ));
        return ConsoleStringReader.getIntInRangeOfCollection(menu, true);
    }

    private void processSelection(Order order, SignatureSandwich picked) {
        ScreenUtils.cls();

        if (confirmAddToOrder(picked)) {
            order.addItem(picked);
            ScreenUtils.printOnCenterOfTheScreen(
                    "Signature sandwich '" + picked.getName() + "' added.");
        } else {
            showNotAddedMessage(picked);

        }
        ScreenUtils.printBox(order.getOrderDetails());
        ScreenUtils.waitTillPressEnter();
        ScreenUtils.cls();
    }


    private boolean confirmAddToOrder(SignatureSandwich sandwich) {
        ScreenUtils.printOnCenterOfTheScreen("Your signature sandwich");
        ScreenUtils.printBox(sandwich.getRepresentation());
        ScreenUtils.printOnCenterOfTheScreen("");
        ScreenUtils.printOnCenterOfTheScreen("Add to order? 1=Yes, 2=No");
        boolean isAdd = ConsoleStringReader.getIntInRangeWithMargin(1, 2) == 1;
        ScreenUtils.cls();
        return isAdd;
    }

    private void showNotAddedMessage(SignatureSandwich sandwich) {
        ScreenUtils.printOnCenterOfTheScreen(
                "Signature sandwich '" + sandwich.getName() + "' was not added to your order.");
    }

}
