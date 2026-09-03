package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

import java.util.ArrayList;

/**
 * Represents a single item within a driver menu.
 * <p>
 * A {@code MenuOption} can function either as an executable action node (holding a {@link Runnable})
 * or as a container node holding a list of sub-options (a sub-menu).
 * </p>
 *
 * @author Gavin Farrell
 * @version 1.0
 */
public final class MenuOption {

    /** The OpMode instance used for logging error state messages. */
    private final BaseOpMode opMode;

    /** The display label for this menu option. */
    private final String name;

    /** The action to execute when this option is selected (null if this option is a sub-menu). */
    private final Runnable action;

    /** The list of nested child menu options (null if this option is an executable action). */
    private final ArrayList<MenuOption> subOptions;

    /**
     * Constructs an executable action menu option.
     *
     * @param opMode The active {@link BaseOpMode} instance.
     * @param name   The display name for the option.
     * @param action The {@link Runnable} task to execute when selected.
     */
    public MenuOption(BaseOpMode opMode, String name, Runnable action) {
        this.opMode = opMode;
        this.name = name;
        this.action = action;
        this.subOptions = null;
    }

    /**
     * Constructs a sub-menu container menu option.
     *
     * @param opMode The active {@link BaseOpMode} instance.
     * @param name   The display name for the sub-menu container.
     */
    public MenuOption(BaseOpMode opMode, String name) {
        this.opMode = opMode;
        this.name = name;
        this.action = null;
        this.subOptions = new ArrayList<>();
    }

    /**
     * Gets the display name of this menu option.
     *
     * @return The option name string.
     */
    public String getName() {
        return name;
    }

    /**
     * Runs the assigned action for this menu option.
     * <p>
     * Logs an error message to the active OpMode if this option represents a sub-menu
     * instead of an executable action.
     * </p>
     */
    public void runAction() {
        if (action == null) {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + MenuOption.class.getName() + ": action cannot be run because it is null", null));
            return;
        }

        action.run();
    }

    /**
     * Gets the list of sub-options contained within this menu item.
     * <p>
     * Logs an error message and returns an empty list if called on an action option
     * rather than a sub-menu container.
     * </p>
     *
     * @return An {@link ArrayList} of nested {@link MenuOption} items.
     */
    public ArrayList<MenuOption> getSubOptions() {
        if (subOptions == null) {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + MenuOption.class.getName() + ": subOptions cannot be returned because it is null", null));
            return new ArrayList<>();
        }

        return subOptions;
    }

    /**
     * Checks whether this option is a sub-menu container holding nested options.
     *
     * @return {@code true} if this option contains sub-options, {@code false} if it is an executable action.
     */
    public boolean hasSubOptions() {
        return subOptions != null;
    }
}