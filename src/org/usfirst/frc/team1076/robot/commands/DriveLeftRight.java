package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.subsystems.FrontBackMotors;

import edu.wpi.first.wpilibj.command.Command;


public class DriveLeftRight extends Command {

	FrontBackMotors frontBack;
	double speed;
	double time;
	double targetTime;

	public DriveLeftRight(FrontBackMotors frontBack, double targetTime, double speed) {
		this.frontBack = frontBack;
		this.speed = speed;
		this.targetTime = targetTime;
		requires(frontBack);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		this.time = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		time += 1 / 50.0;
		frontBack.setSpeed(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return time >= targetTime;
	}

	// Called once after isFinished returns true
	protected void end() {
		frontBack.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
