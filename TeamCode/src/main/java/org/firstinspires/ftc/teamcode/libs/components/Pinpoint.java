package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.libs.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.libs.core.Component;
import org.firstinspires.ftc.teamcode.libs.util.LogEntry;

/**
 * A wrapper component for managing the goBILDA Pinpoint Odometry Computer in FTC OpModes.
 * <p>
 * Handles initializing offsets, encoder directions, and resolutions, while providing
 * methods to query robot position (X, Y, Heading) and log telemetry diagnostics.
 * </p>
 *
 * @author Gavin Farrell
 * @version 1.0
 */
public final class Pinpoint extends Component {

    /** The underlying {@link GoBildaPinpointDriver} hardware instance. */
    private final GoBildaPinpointDriver pinpoint;

    /**
     * Constructs a new Pinpoint component, configures its physical offsets, pod types, and encoder directions,
     * and resets position tracking and the internal IMU.
     *
     * @param opMode        The active {@link BaseOpMode} instance.
     * @param componentName The hardware map name of the Pinpoint computer.
     * @param podType       The type of goBILDA odometry pods attached (resolution configuration).
     * @param xPodDirection The directional orientation of the X-axis encoder pod.
     * @param yPodDirection The directional orientation of the Y-axis encoder pod.
     * @param xOffset       The physical offset of the X pod relative to the robot center in centimeters.
     * @param yOffset       The physical offset of the Y pod relative to the robot center in centimeters.
     */
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

        if (pinpoint != null) {

            pinpoint.setOffsets(xOffset, yOffset, DistanceUnit.CM);
            pinpoint.resetPosAndIMU();
            pinpoint.setEncoderResolution(podType);
            pinpoint.setEncoderDirections(xPodDirection, yPodDirection);

        }

    }

    /**
     * Logs current X position, Y position, and heading to the OpMode's diagnostic telemetry log.
     */
    public void getDebugValues() {

        opMode.logData(new LogEntry(LogEntry.EntryType.DEBUG, Double.toString(getX()), "Pinpoint X: "));
        opMode.logData(new LogEntry(LogEntry.EntryType.DEBUG, Double.toString(getY()), "Pinpoint Y:"));
        opMode.logData(new LogEntry(LogEntry.EntryType.DEBUG, Double.toString(getHeading()), "Pinpoint Heading: "));

    }

    /**
     * Gets the current estimated X coordinate of the robot.
     *
     * @return The X position in centimeters.
     */
    public double getX() {
        return pinpoint.getPosition().getX(DistanceUnit.CM);
    }

    /**
     * Gets the current estimated Y coordinate of the robot.
     *
     * @return The Y position in centimeters.
     */
    public double getY() {
        return pinpoint.getPosition().getY(DistanceUnit.CM);
    }

    /**
     * Gets the current estimated heading angle of the robot.
     *
     * @return The heading angle in degrees.
     */
    public double getHeading() {
        return pinpoint.getPosition().getHeading(AngleUnit.DEGREES);
    }

    /**
     * Overrides or resets the current position and orientation tracking to a specified starting pose.
     *
     * @param x       The new starting X position in centimeters.
     * @param y       The new starting Y position in centimeters.
     * @param heading The new starting heading in degrees.
     */
    public void setStartingPose(double x, double y, double heading) {pinpoint.setPosition(new Pose2D(DistanceUnit.CM, x, y, AngleUnit.DEGREES, heading));}

}