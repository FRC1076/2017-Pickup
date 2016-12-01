package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.subsystems.FrontBackMotors;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class RotateCommand extends Command {

	LeftRightMotors leftRight;
	FrontBackMotors frontBack;
	double angle;
	
	/**
	 * Creates a RotateCommand giving it the subsystems and angle it requires.
	 * @param leftRight
	 * @param frontBack
	 * @param angle measured in degrees clockwise.
	 */
    public RotateCommand(LeftRightMotors leftRight, FrontBackMotors frontBack, double angle) {
    	requires(leftRight);
    	requires(frontBack);
    	this.leftRight = leftRight;
    	this.frontBack = frontBack;
    	this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
