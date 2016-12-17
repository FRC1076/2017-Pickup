package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.subsystems.DoorPneumatic;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Controls whether or not to open or close the door.
 */
public class DoorMovement extends Command {

	DoorPneumatic doorPiston; 
	boolean isFinished; 
	
    public DoorMovement(DoorPneumatic doormovement) {
    	this.doorPiston = doormovement;
        requires(doormovement);
        this.isFinished = false;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (doorPiston.value() == true) {
    	doorPiston.close();
    	} else {
    		doorPiston.open();
    	}
    	this.isFinished = true;
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
