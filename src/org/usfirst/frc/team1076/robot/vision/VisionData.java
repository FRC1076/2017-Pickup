package org.usfirst.frc.team1076.robot.vision;

import org.json.JSONException;
import org.json.JSONObject;

public class VisionData {
    public enum VisionStatus {
        LEFT, RIGHT, OK, ERROR
    }

    int heading;
    double range;
    VisionStatus status;

    public VisionData(JSONObject json) {
        try {
            switch (json.getString("status")) {
            case "left":
                status = VisionStatus.LEFT;
            case "right":
                status = VisionStatus.RIGHT;
            case "ok":
                status = VisionStatus.OK;
            case "error":
            default:
                status = VisionStatus.ERROR;
            }
            heading = json.getInt("heading");
            range = json.getDouble("range");
        } catch (JSONException e) {
            status = VisionStatus.ERROR;
            heading = 0;
            range = 0;
        }
    }

    public int getHeading() {
        return heading;
    }

    public double getRange() {
        return range;
    }

    public VisionStatus getStatus() {
        return status;
    }
}
