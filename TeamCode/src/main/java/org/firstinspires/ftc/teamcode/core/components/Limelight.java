package org.firstinspires.ftc.teamcode.core.components;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import org.firstinspires.ftc.teamcode.core.core.BaseOpMode;
import org.firstinspires.ftc.teamcode.core.core.Component;

import java.util.ArrayList;

/**
 *
 * We are oing to use one neural pipeline to maybe detect gam eobjects and othe rrobots a the same time
 * One othe rpipeline for apirl tags
 *
 * Autonoumous ai will switch between pipelines when it needs to.
 */
public final class Limelight extends Component {

    public enum Pipeline {
        COLOR_BLOB(0, new ArrayList<ColorBlobData>()),
        FIDUCIAL(1, new ArrayList<>());

        private final int pipelineIndex;

        private final ArrayList<?> pipelineDataMap;

        Pipeline(int pipelineIndex, ArrayList<?> pipelineDataMap) {

            this.pipelineIndex = pipelineIndex;
            this.pipelineDataMap = pipelineDataMap;

        }

        public int getPipelineIndex() {

            return pipelineIndex;

        }

        public ArrayList<?> getPipelineDataMap() {

            return pipelineDataMap;

        }

    }

    private final Limelight3A limelight;
    private LLResult currentResult;

    public Limelight(BaseOpMode opMode, String componentName) {

        super(opMode, componentName);

        limelight = getHardware(Limelight3A.class);

        if(limelight != null){

            limelight.setPollRateHz(100);
            limelight.start();

        }

    }

    public void update() {

       currentResult = limelight.getLatestResult();

    }

    public void setPipeline(Pipeline pipeline) {

        limelight.pipelineSwitch(pipeline.getPipelineIndex());

    }

    public void getColorBlobData(Pipeline pipeline) {

        if(currentResult != null && currentResult.isValid()) {

            for(LLResultTypes.ColorResult blob : currentResult.getColorResults()) {



            }
        }

    }

    public void getFiducialData() {

        if(currentResult != null && currentResult.isValid()) {

            for(LLResultTypes.FiducialResult fiducial : currentResult.getFiducialResults()) {



            }

        }

    }


    static class ColorBlobData {

        private final double tx;
        private final double ty;
        private final double ta;

        public ColorBlobData(double tx, double ty, double ta) {

            this.tx = tx;
            this.ty = ty;
            this.ta = ta;

        }

        public double getTx() {

            return tx;

        }

        public double getTy() {

            return ty;

        }

        public double getTa() {

            return ta;

        }

    }

}


