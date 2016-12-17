package org.usfirst.frc.team1076.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CancelCommand extends Command {

	Command[] commandsToCancel;
	
    public CancelCommand(Command[] commandsToCancel) {
    	this.commandsToCancel = commandsToCancel;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	for (Command command : commandsToCancel) {
    		command.cancel();
    	}
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
    protected void interrupted() { }
}
