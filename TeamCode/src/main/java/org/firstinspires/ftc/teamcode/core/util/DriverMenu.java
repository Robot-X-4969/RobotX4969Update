package org.firstinspires.ftc.teamcode.core.util;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

import java.util.ArrayList;
import java.util.Stack;

public final class DriverMenu {
    private final BaseOpMode opMode;
    private final ArrayList<MenuOption> menuOptions;
    private ArrayList<MenuOption> currentMenu;
    private Stack<ArrayList<MenuOption>> menus;
    private int currentIndex;

    public DriverMenu(BaseOpMode opMode, ArrayList<MenuOption> menuOptions) {

        this.opMode = opMode;
        this.menuOptions = menuOptions;

        currentMenu = menuOptions;
        menus = new Stack<>();
        currentIndex = 0;

    }

    public void displayMenu() {

        for(int i = 0; i < currentMenu.size(); i++) {

            MenuOption option = currentMenu.get(i);

            String prefix = (i == currentIndex) ? ">" : "  ";

            opMode.telemetry.addLine(prefix + option.getName());

        }


    }

    public void updateMenu() {

        if(opMode.getDriverStation().getGamepad1().getDpadDown().justPressed()) {

            currentIndex++;

            if(currentIndex > currentMenu.size() - 1) {

                currentIndex = currentMenu.size() - 1;

            }

        } else if(opMode.getDriverStation().getGamepad1().getDpadUp().justPressed()) {

            currentIndex--;

            if(currentIndex < 0) {

                currentIndex = 0;

            }

        } else if(opMode.getDriverStation().getGamepad1().getA().justPressed()) {

            MenuOption selectedOption = currentMenu.get(currentIndex);

            if(selectedOption.hasSubOptions()) {

                menus.push(currentMenu);

                currentMenu = selectedOption.getSubOptions();

                currentIndex = 0;

            } else {

                selectedOption.runAction();

            }

        } else if(opMode.getDriverStation().getGamepad1().getB().justPressed()) {

            currentMenu = menus.isEmpty() ? menuOptions : menus.pop();

            currentIndex = 0;

        }

    }



}


























