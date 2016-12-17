package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.SonarReceiver;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command checks to see if the robot is close to the trash
 * can, and when it is, it triggers another command.
 */
public class SonarTrigger extends Command {
	
	enum State {
		BEFORE_CAN,
		IN_CAN,
		AFTER_CAN;
	}

	int threshold;
	SonarReceiver sonar;
	Command command;
	boolean shouldStart;
	State state;
	
    public SonarTrigger(int threshold, SonarReceiver sonar, Command command) {
    	this.threshold = threshold;
    	this.sonar = sonar;
    	this.command = command;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	state = State.BEFORE_CAN;
    	shouldStart = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		sonar.receive();
		switch (state) {
		case BEFORE_CAN:
			if (sonar.distance() <= threshold) {
		    	state = State.IN_CAN;
			}
			break;
		case IN_CAN:
			if (sonar.distance() > threshold) {
		    	state = State.AFTER_CAN;
			}
			break;
		case AFTER_CAN:
			if (shouldStart) {
				shouldStart = false;
				command.start();
				this.cancel();
			}
			break;
		}
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == State.AFTER_CAN;
    }

    // Called once after isFinished returns true
    protected void end() { }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() { }
}
