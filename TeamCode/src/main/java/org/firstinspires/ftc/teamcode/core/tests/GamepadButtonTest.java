package org.firstinspires.ftc.teamcode.core.tests;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;

@TeleOp(name = "Gamepad Button Test", group = "Tests")
public class GamepadButtonTest extends BaseOpMode {

    @Override
    public void loop() {

        driverStation.update();

        telemetry.addData("A Button Pressed", driverStation.getGamepad1().getA().isPressed());
        telemetry.addData("B Button Pressed", driverStation.getGamepad1().getB().isPressed());
        telemetry.addData("X Button Pressed", driverStation.getGamepad1().getX().isPressed());
        telemetry.addData("Y Button Pressed", driverStation.getGamepad1().getY().isPressed());
        telemetry.addData("Left Bumper Pressed", driverStation.getGamepad1().getLeftBumper().isPressed());
        telemetry.addData("Right Bumper Pressed", driverStation.getGamepad1().getRightBumper().isPressed());
        telemetry.addData("Left Trigger Pressed", driverStation.getGamepad1().getLeftTriggerPressure() > 0);
        telemetry.addData("Right Trigger Pressed", driverStation.getGamepad1().getRightTriggerPressure() > 0);
        telemetry.addData("D-Pad Up Pressed", driverStation.getGamepad1().getDpadUp().isPressed());
        telemetry.addData("D-Pad Down Pressed", driverStation.getGamepad1().getDpadDown().isPressed());
        telemetry.addData("D-Pad Left Pressed", driverStation.getGamepad1().getDpadLeft().isPressed());
        telemetry.addData("D-Pad Right Pressed", driverStation.getGamepad1().getDpadRight().isPressed());
        telemetry.addData("Left Stick Button Pressed", driverStation.getGamepad1().getLeftStickButton().isPressed());
        telemetry.addData("Right Stick Button Pressed", driverStation.getGamepad1().getRightStickButton().isPressed());
        telemetry.addData("Left Stick X", driverStation.getGamepad1().getLeftStickX());
        telemetry.addData("Left Stick Y", driverStation.getGamepad1().getLeftStickY());
        telemetry.addData("Right Stick X", driverStation.getGamepad1().getRightStickX());
        telemetry.addData("Right Stick Y", driverStation.getGamepad1().getRightStickY());
        telemetry.addData("Start Button Pressed", driverStation.getGamepad1().getStartButton().isPressed());
        telemetry.addData("Back Button Pressed", driverStation.getGamepad1().getBackButton().isPressed());
        telemetry.addData("Guide Button Pressed", driverStation.getGamepad1().getGuideButton().isPressed());

        telemetry.update();

    }

}
