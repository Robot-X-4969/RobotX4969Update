package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Manages an interactive telemetry-based driver menu for FTC OpModes.
 * <p>
 * Supports navigating through parent and nested sub-menu options using
 * the Driver Station gamepad controls (DPAD and action buttons).
 * </p>
 *
 * @author Gavin Farrell
 * @version 1.0
 */
public final class DriverMenu {

    /** The OpMode instance used to render telemetry and access gamepads. */
    private final BaseOpMode opMode;

    /** The root list of top-level menu options. */
    private final ArrayList<MenuOption> menuOptions;

    /** The list of menu options currently being displayed and navigated. */
    private ArrayList<MenuOption> currentMenu;

    /** A stack used to track navigation history for returning to parent menus. */
    private Stack<ArrayList<MenuOption>> menus;

    /** The zero-based index of the currently highlighted menu option. */
    private int currentIndex;

    /**
     * Constructs a new DriverMenu with a specified root set of options.
     *
     * @param opMode      The active {@link BaseOpMode} instance.
     * @param menuOptions The list of top-level {@link MenuOption} items.
     */
    public DriverMenu(BaseOpMode opMode, ArrayList<MenuOption> menuOptions) {
        this.opMode = opMode;
        this.menuOptions = menuOptions;

        currentMenu = menuOptions;
        menus = new Stack<>();
        currentIndex = 0;
    }

    /**
     * Renders the current menu options to Driver Station telemetry.
     * <p>
     * Displays a {@code >} cursor next to the currently selected option
     * and indents unselected options.
     * </p>
     */
    public void displayMenu() {
        for (int i = 0; i < currentMenu.size(); i++) {
            MenuOption option = currentMenu.get(i);

            String prefix = (i == currentIndex) ? ">" : "  ";

            opMode.telemetry.addLine(prefix + option.getName());
        }
    }

    /**
     * Polls gamepad input to update navigation state or trigger option actions.
     * <ul>
     *   <li><b>DPAD Down:</b> Moves selection down.</li>
     *   <li><b>DPAD Up:</b> Moves selection up.</li>
     *   <li><b>A Button:</b> Selects current option (opens sub-menu or executes action).</li>
     *   <li><b>B Button:</b> Navigates back to the previous parent menu.</li>
     * </ul>
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
}