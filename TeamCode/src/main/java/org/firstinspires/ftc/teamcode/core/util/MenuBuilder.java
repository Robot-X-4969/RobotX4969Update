package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

import java.util.ArrayList;

/**
 * A fluent builder for constructing hierarchical {@link DriverMenu} structures.
 * <p>
 * Supports nesting sub-menus and assigning actions to executable options, while providing
 * error logging and validation if the hierarchy is defined incorrectly.
 * </p>
 *
 * @author Gavin Farrell
 * @version 1.0
 */
public final class MenuBuilder {

    /** The OpMode instance used for logging errors and managing OpMode lifecycle stops. */
    private final BaseOpMode opMode;

    /** The parent {@link MenuBuilder} node in the menu tree, or {@code null} if this is the root menu. */
    private final MenuBuilder parent;

    /** The display name of this menu option or sub-menu node. */
    private final String optionName;

    /** The action to execute when this option is selected (null if this node is a sub-menu). */
    private final Runnable action;

    /** The list of child menu builders contained within this sub-menu (null if this node is an action). */
    private final ArrayList<MenuBuilder> builders;

    /**
     * Constructs an action node within the menu builder tree.
     *
     * @param opMode     The active {@link BaseOpMode} instance.
     * @param optionName The display name for this action option.
     * @param action     The {@link Runnable} to execute when selected.
     * @param parent     The parent {@link MenuBuilder} node containing this action.
     */
    public MenuBuilder(BaseOpMode opMode, String optionName, Runnable action, MenuBuilder parent) {
        this.opMode = opMode;
        this.parent = parent;
        this.optionName = optionName;
        this.action = action;
        this.builders = null;
    }

    /**
     * Constructs a sub-menu container node within the menu builder tree.
     *
     * @param opMode     The active {@link BaseOpMode} instance.
     * @param optionName The display name for this sub-menu option.
     * @param parent     The parent {@link MenuBuilder} node containing this sub-menu, or {@code null} if root.
     */
    public MenuBuilder(BaseOpMode opMode, String optionName, MenuBuilder parent) {
        this.opMode = opMode;
        this.parent = parent;
        this.optionName = optionName;
        this.action = null;
        this.builders = new ArrayList<>();
    }

    /**
     * Factory method to initialize a new root-level menu builder.
     *
     * @param opMode The active {@link BaseOpMode} instance.
     * @return A new root {@link MenuBuilder} instance.
     */
    public static MenuBuilder createMenu(BaseOpMode opMode) {
        return new MenuBuilder(opMode, "Root Menu", null);
    }

    /**
     * Adds an executable action option to the current sub-menu level.
     *
     * @param optionName The display label for the action.
     * @param action     The {@link Runnable} to execute when selected.
     * @return This {@link MenuBuilder} instance for method chaining.
     */
    public MenuBuilder addAction(String optionName, Runnable action) {
        if (isSubMenu()) {
            MenuBuilder menuAction = new MenuBuilder(opMode, optionName, action, this);
            builders.add(menuAction);
            return this;
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot add menu option to a non-submenu", null));
            opMode.requestOpModeStop();
        }

        return this;
    }

    /**
     * Creates and shifts scope into a new nested sub-menu.
     *
     * @param optionName The display label for the new sub-menu.
     * @return The newly created child {@link MenuBuilder} instance representing the sub-menu.
     */
    public MenuBuilder addSubMenu(String optionName) {
        if (isSubMenu()) {
            MenuBuilder subMenu = new MenuBuilder(opMode, optionName, this);
            builders.add(subMenu);
            return subMenu;
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot add submenu to a non-submenu", null));
            opMode.requestOpModeStop();
        }

        return this;
    }

    /**
     * Concludes the current sub-menu scope and ascends back to the parent menu builder.
     *
     * @return The parent {@link MenuBuilder} instance.
     */
    public MenuBuilder endSubMenu() {
        if (isSubMenu() && parent != null) {
            return parent;
        }

        if (parent == null) {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot end submenu on root menu", null));
            opMode.requestOpModeStop();
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot end submenu on a non-submenu", null));
            opMode.requestOpModeStop();
        }

        return this;
    }

    /**
     * Compiles the configuration and constructs the final {@link DriverMenu} instance.
     * <p>
     * Must be invoked on the root-level menu builder.
     * </p>
     *
     * @return The configured {@link DriverMenu} instance ready for runtime display.
     */
    public DriverMenu buildMenu() {
        if (parent == null) {
            return new DriverMenu(opMode, this.compile());
        } else {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot build menu on a non-root menu", null));
            opMode.requestOpModeStop();
        }

        return new DriverMenu(opMode, new ArrayList<>());
    }

    /**
     * Recursively processes the builder chain into a nested hierarchy of {@link MenuOption} objects.
     *
     * @return An {@link ArrayList} of root-level {@link MenuOption} items with attached sub-options.
     */
    public ArrayList<MenuOption> compile() {
        ArrayList<MenuOption> compiledOptions = new ArrayList<>();

        if (builders == null) {
            opMode.logData(new LogEntry(LogEntry.EntryType.ERROR, "[ERROR] in " + MenuBuilder.class.getName() + ": cannot compile a non-submenu", null));
            opMode.requestOpModeStop();
            return compiledOptions;
        }

        for (MenuBuilder builder : this.builders) {
            if (builder.isSubMenu()) {
                MenuOption subMenuOption = new MenuOption(builder.opMode, builder.optionName);
                ArrayList<MenuOption> subMenuOptions = builder.compile();

                subMenuOption.getSubOptions().addAll(subMenuOptions);
                compiledOptions.add(subMenuOption);
            } else {
                compiledOptions.add(new MenuOption(builder.opMode, builder.optionName, builder.action));
            }
        }

        return compiledOptions;
    }

    /**
     * Checks whether this builder node represents a sub-menu container rather than an executable action.
     *
     * @return {@code true} if this node is a sub-menu container, {@code false} if it is an action option.
     */
    public boolean isSubMenu() {
        return builders != null;
    }
}