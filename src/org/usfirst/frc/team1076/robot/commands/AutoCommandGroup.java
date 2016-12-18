package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.subsystems.FrontBackMotors;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;
import org.usfirst.frc.team1076.robot.vision.VisionReceiver;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCommandGroup extends CommandGroup {
	
	/**
	 * 
	 * @param frontBack
	 * @param leftRight
	 * @param vision
	 */
	public AutoCommandGroup(FrontBackMotors frontBack, LeftRightMotors leftRight, VisionReceiver vision) {
		this(frontBack, leftRight, vision, 0.5, 2, 1, 0.3);
	}
	
	/**
	 * 
	 * @param frontBack
	 * @param leftRight
	 * @param vision
	 * @param speed
	 * @param time
	 * @param turnFactor
	 */
    public AutoCommandGroup(FrontBackMotors frontBack, LeftRightMotors leftRight, VisionReceiver vision,
    		double speed, double time, double turnFactor, double turnSpeed) {
    	// This is inelegant, but this should be enough to run for the 15 seconds of autonomous.
    	for (int i = 0; i < 10; ++i) {
        	DriveForwardBackward forward = new DriveForwardBackward(leftRight, time, speed);
        	RotateWithVision rotate = new RotateWithVision(frontBack, leftRight, vision);
        	rotate.timeFactor = turnFactor;
        	rotate.speed = turnSpeed;
    		addSequential(forward);
    		addSequential(rotate);
    	}
    }
	
}
