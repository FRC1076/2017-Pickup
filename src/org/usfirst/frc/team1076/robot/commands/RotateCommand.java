package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;

import edu.wpi.first.wpilibj.command.Command;

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
    	requires(leftRight);
    	this.leftRight = leftRight;
    	this.targetTime = targetTime;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.time = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	time += 1/50.0;
    	
    	final double width = 23;
    	final double height = 14.5;
    	final double frontBackSpeed = height / width;
    	final double leftRightSpeed = 1;
    	
    	leftRight.setLeftSpeed(speed * leftRightSpeed);
    	leftRight.setRightSpeed(-speed * leftRightSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return time >= targetTime;
    }

    // Called once after isFinished returns true
    protected void end() {
    	leftRight.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
