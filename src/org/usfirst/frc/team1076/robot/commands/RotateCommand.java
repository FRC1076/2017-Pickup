package org.usfirst.frc.team1076.robot.commands;

import org.strongback.command.Command;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;

/**
 *
 */
public class RotateCommand extends Command {

	LeftRightMotors leftRight;
	double targetTime;
	double time;
	double speed;
	
	/**
	 * Creates a RotateCommand giving it the subsystems and angle it requires.
	 * @param leftRight
	 * @param frontBack
	 * @param targetTime measured in seconds.
	 * @param speed in the range of -1 to 1. Positive numbers rotate clockwise, negative numbers rotate counterclockwise.
	 */
    public RotateCommand(LeftRightMotors leftRight, double targetTime, double speed) {
    	super(leftRight);
    	this.leftRight = leftRight;
    	this.targetTime = targetTime;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	this.time = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    public boolean execute() {
    	time += 1/50.0;
    	
    	leftRight.setLeftSpeed(speed);
    	leftRight.setRightSpeed(-speed);
    	return isFinished();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return time >= targetTime;
    }

    // Called once after isFinished returns true
    public void end() {
    	leftRight.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    public void interrupted() {
    	end();
    }
}
