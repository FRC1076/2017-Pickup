package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.Gamepad;
import org.usfirst.frc.team1076.robot.subsystems.FrontBackMotors;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Controls the robot using a joy stick controller.
 * This command is intended to run continuously for the entire lifetime of the teleop mode.
 */
public class TeleopCommand extends Command {

	FrontBackMotors frontBack;
	LeftRightMotors leftRight;
	Gamepad gamepad;
	
    public TeleopCommand(Gamepad gamepad, FrontBackMotors frontBack , LeftRightMotors leftRight ) {
         requires(frontBack);
         requires(leftRight);
         this.gamepad = gamepad;
         this.frontBack = frontBack;
         this.leftRight = leftRight;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Strafing
    	
    	// Rotation
    	
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
