package org.usfirst.frc.team1076.robot.vision;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * This class processes JSON packets for vision.
 */
public class VisionData {
    public enum VisionStatus {
        LEFT, RIGHT, OK, ERROR
    }

    int heading;
    double range;
    VisionStatus status;

    /**
     * Extracts data from a JSONObject. This object must have
     * the keys "status", "heading", and "range".  
     */
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
        } catch (JSONException e) { // Malformed JSON packet, assume no useful data
            status = VisionStatus.ERROR;
            heading = 0;
            range = 0;
        }
    }
    
    /**
     * Extracts data from a string formated like a JSON blob.
     */
    public VisionData(String json) {
    	this(new JSONObject(new JSONTokener(json)));
    }
    
    /** 
     * The angle of the target, in degrees, from the middle of the robot.
     * Should return from -180 to 180
     */
    public int getHeading() {
        return heading;
    }

    /**
     * The distance to the target, measured in inches
     */
    public double getRange() {
        return range;
    }

    /**
     * The status of the message.
     * Left and right indicate that the robot is on that side.
     * Ok indicates that the robot is head on
     * Error indicates that the goal was not found for some reason
     */
    public VisionStatus getStatus() {
        return status;
    }
}
