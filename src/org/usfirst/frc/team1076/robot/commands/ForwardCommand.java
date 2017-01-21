package org.usfirst.frc.team1076.robot.commands;

import org.strongback.command.Command;
import org.strongback.components.Motor;

public class ForwardCommand extends Command {
	Motor leftmotor; 
	Motor rightmotor; 
	double speed;
	double time = 0; // time command has been running
	double targetTime;
	
	public ForwardCommand(Motor leftmotor, Motor rightmotor, double speed, double targetTime) {	
		this.leftmotor = leftmotor;
		this.rightmotor = rightmotor;
		this.speed = speed; 
	}
	
	
	public void initialize() {
		time = 0; 
	}
	
	@Override
	public boolean execute() {
	     leftmotor.setSpeed(speed);
	     rightmotor.setSpeed(speed);
	     time += 1/50.0;
        return isFinished();
	}
	
	protected boolean isFinished() {
		return time >= targetTime; 
	}
	
	

}
