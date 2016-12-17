package org.usfirst.frc.team1076.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DashboardMessage extends Command {

	String key = "message";
	String message;
	
	public DashboardMessage(String message, String key) {
		this.message = message;
		this.key = key;
	}
	
    public DashboardMessage(String message) {
    	this.message = message;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	SmartDashboard.putString(key, message);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() { }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
