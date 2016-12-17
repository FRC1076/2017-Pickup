package org.usfirst.frc.team1076.robot.commands;

import java.io.IOException;

import org.usfirst.frc.team1076.robot.SonarReceiver;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * This command checks to see if the robot is close to the trash
 * can, and when it is, it triggers another command.
 */
public class SonarTrigger extends CommandGroup {

	
	int threshold;
	SonarReceiver sonar;
	Command command;
	boolean shouldFinish;
	
	
    public SonarTrigger(int threshold, SonarReceiver sonar, Command command) {
    	this.threshold = threshold;
    	this.sonar = sonar;
    	this.command = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shouldFinish = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			sonar.receive();
			if (sonar.distance() <= threshold){
	    		addSequential(command);
	    		shouldFinish = true;
	    		// tell isfinished that we're done
	    	}
		} catch (IOException e) {
			
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shouldFinish;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
