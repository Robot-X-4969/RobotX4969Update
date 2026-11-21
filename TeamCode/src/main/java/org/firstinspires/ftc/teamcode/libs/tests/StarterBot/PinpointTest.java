package org.firstinspires.ftc.teamcode.libs.tests.StarterBot;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.libs.drive.MecanumDrive;
import org.firstinspires.ftc.teamcode.libs.util.DriverMenu;
@TeleOp(name = "Pinpoint System", group = "tests")
public class PinpointTest extends RobotTeleOp {

    MecanumDrive mecanumDrive;

    @Override
    public DriverMenu initDriverMenu() {
        return null;
    }

    @Override
    public void initModules() {

        mecanumDrive = new MecanumDrive(this);

    }

}
