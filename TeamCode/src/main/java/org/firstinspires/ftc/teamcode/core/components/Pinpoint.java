package org.firstinspires.ftc.teamcode.core.components;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.core.core.Component;
import org.firstinspires.ftc.teamcode.core.util.LogEntry;

public final class Pinpoint extends Component {

    private final GoBildaPinpointDriver pinpoint;

    public Pinpoint(
            BaseOpMode opMode,
            String componentName,
            GoBildaPinpointDriver.GoBildaOdometryPods podType,
            GoBildaPinpointDriver.EncoderDirection xPodDirection,
            GoBildaPinpointDriver.EncoderDirection yPodDirection,
            int xOffset,
            int yOffset
    ) {

        super(opMode, componentName);

        pinpoint = getHardware(GoBildaPinpointDriver.class);

        if(pinpoint != null) {

            pinpoint.setOffsets(xOffset, yOffset, DistanceUnit.CM);

            pinpoint.resetPosAndIMU();

            pinpoint.setEncoderResolution(podType);
            pinpoint.setEncoderDirections(xPodDirection, yPodDirection);

        }

    }

    public void getDebugValues() {

        opMode.logData(new LogEntry(BaseOpMode.EntryType.DEBUG, Double.toString(getX()), "Pinpoint X: "));
        opMode.logData(new LogEntry(BaseOpMode.EntryType.DEBUG, Double.toString(getY()), "Pinpoint Y:"));
        opMode.logData(new LogEntry(BaseOpMode.EntryType.DEBUG, Double.toString(getHeading()), "Pinpoint Heading: "));

    }

    public double getX() {

        return pinpoint.getPosition().getX(DistanceUnit.CM);

    }

    public double getY() {

        return pinpoint.getPosition().getY(DistanceUnit.CM);

    }

    public double getHeading() {

        return pinpoint.getPosition().getHeading(AngleUnit.DEGREES);

    }

    public void setStartingPose(double x, double y, double heading) {

        pinpoint.setPosition(new Pose2D(DistanceUnit.CM, x, y, AngleUnit.DEGREES, heading));

    }

}
