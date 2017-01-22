package org.usfirst.frc.team1076.robot.commands;

import org.strongback.command.Command;
import org.strongback.components.Motor;
import org.usfirst.frc.team1076.robot.vision.VisionData;

public class ForwardWithVision extends Command {
	Motor leftmotor; 
	Motor rightmotor; 
	double speed;
	double time = 0; // time command has been running
	double targetTime;
	VisionData vision;
	
	public ForwardWithVision(Motor leftmotor, Motor rightmotor, double speed, double targetTime, VisionData data) {	
		this.leftmotor = leftmotor;
		this.rightmotor = rightmotor;
		this.speed = speed;
		this.vision = vision;
	}
	
	
	public void initialize() {
		time = 0; 
	}
	
	@Override
	public boolean execute() { 
	     leftmotor.setSpeed(speed*(1+vision.getHeading()/15));
	     rightmotor.setSpeed(speed*(1-vision.getHeading()/15));
	     time += 1/50.0;
        return isFinished();
	}
	
	protected boolean isFinished() {
		return time >= targetTime; 
	}
}
