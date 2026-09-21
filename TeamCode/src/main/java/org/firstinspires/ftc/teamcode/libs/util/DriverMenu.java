package org.firstinspires.ftc.teamcode.libs.util;

import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Provides an interactive, hierarchical menu system for driver station telemetry.
 * <p>
 * Manages nested menu structures using a stack-based navigation model and controller input
 * to transition between submenus or execute option actions.
 * </p>
 *
 * @author Gavin Farrell
 * @version 1.0
 */
public final class DriverMenu {

    /** The OpMode context used for telemetry rendering and driver station controller access. */
    private final BaseOpMode opMode;

    /** The root list of top-level menu options. */
    private final ArrayList<MenuOption> menuOptions;

    /** The currently active list of menu options being displayed. */
    private ArrayList<MenuOption> currentMenu;

    /** Stack storing previous menu levels to enable back navigation. */
    private final Stack<ArrayList<MenuOption>> menus;

    /** The index of the currently highlighted option within {@link #currentMenu}. */
    private int currentIndex;

    /** The largest size encountered among all submenus, used to calculate line padding. */
    private int maxItems = 0;

    /**
     * Constructs a {@code DriverMenu} instance and calculates maximum menu depth padding.
     *
     * @param opMode      The active {@link BaseOpMode} instance.
     * @param menuOptions The top-level list of {@link MenuOption} items.
     */
    public DriverMenu(BaseOpMode opMode, ArrayList<MenuOption> menuOptions) {

        this.opMode = opMode;
        this.menuOptions = menuOptions;
        this.currentMenu = menuOptions;
        this.menus = new Stack<>();
        this.currentIndex = 0;

        getMaxMenuItems(menuOptions);

    }

    /**
     * Prints the active menu state to telemetry, highlighting the selected item
     * with a cursor and applying blank lines to match the maximum menu height.
     */
    public void displayMenu() {

        opMode.telemetry.addLine("============== DRIVER MENU ==============");

        for (int i = 0; i < currentMenu.size(); i++) {

            MenuOption option = currentMenu.get(i);

            opMode.telemetry.addLine(((i == currentIndex) ? ">" : "  ") + option.getName());

        }

        if(currentMenu.size() < maxItems) {

            for (int i = currentMenu.size(); i < maxItems; i++) {

                opMode.telemetry.addLine("");

            }

        }

    }

    /**
     * Evaluates gamepad inputs to adjust navigation state, enter submenus,
     * execute actions, or return to parent menus.
     */
    public void updateMenu() {

        if (opMode.getDriverStation().getGamepad1().getDpadDown().justPressed()) {

            currentIndex++;

            if (currentIndex > currentMenu.size() - 1) {
                currentIndex = currentMenu.size() - 1;
            }

        } else if (opMode.getDriverStation().getGamepad1().getDpadUp().justPressed()) {

            currentIndex--;

            if (currentIndex < 0) {
                currentIndex = 0;
            }

        } else if (opMode.getDriverStation().getGamepad1().getA().justPressed()) {

            MenuOption selectedOption = currentMenu.get(currentIndex);

            if (selectedOption.hasSubOptions()) {

                menus.push(currentMenu);

                currentMenu = selectedOption.getSubOptions();

                currentIndex = 0;

            } else {

                selectedOption.runAction();

            }

        } else if (opMode.getDriverStation().getGamepad1().getB().justPressed()) {

            currentMenu = menus.isEmpty() ? menuOptions : menus.pop();

            currentIndex = 0;

        }

    }

    /**
     * Recursively computes the maximum item count across all nested menu levels.
     *
     * @param menu The menu list to inspect.
     */
    private void getMaxMenuItems(ArrayList<MenuOption> menu) {

        if (menu.size() > maxItems) {

            maxItems = menu.size();

        }

        for (MenuOption option : menu) {

            if (option.hasSubOptions()) {

                getMaxMenuItems(option.getSubOptions());

            }

        }

    }

}