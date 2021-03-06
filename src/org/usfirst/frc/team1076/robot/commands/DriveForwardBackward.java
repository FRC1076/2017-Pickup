package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This is a command for driving forwards or backwards.
 * 
 * @author Ella
 *
 */
public class DriveForwardBackward extends Command {

	LeftRightMotors leftRight;
	double targetTime;
	double time;
	double speed;

	/**
	 * 
	 * @param leftRight
	 *            is the motor object to control.
	 * @param targetTime
	 *            measured in seconds.
	 * @param speed
	 *            is in the range of -1 to 1 (positive is forwards).
	 */
	public DriveForwardBackward(LeftRightMotors leftRight, double targetTime, double speed) {
		this.leftRight = leftRight;
		this.targetTime = targetTime;
		this.speed = speed;
		requires(leftRight);
	}

	protected void initialize() {
		time = 0;
	}

	protected void execute() {
		leftRight.setSpeed(speed);
		time += 1/50.0;
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
