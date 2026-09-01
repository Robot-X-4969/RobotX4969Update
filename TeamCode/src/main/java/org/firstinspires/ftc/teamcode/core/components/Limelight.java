package org.firstinspires.ftc.teamcode.core.components;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.core.core.Component;

public final class Limelight extends Component {

    private final Limelight3A limelight;

    public Limelight(BaseOpMode opMode, String componentName) {

        super(opMode, componentName);

        limelight = getHardware(Limelight3A.class);

    }

    public void setPipeline(int pipelineNumber) {

        limelight.pipelineSwitch(pipelineNumber);

    }

}
