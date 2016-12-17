package org.usfirst.frc.team1076.robot.commands;

import java.io.IOException;
import java.net.SocketException;

import org.usfirst.frc.team1076.robot.subsystems.FrontBackMotors;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;
import org.usfirst.frc.team1076.robot.vision.VisionReceiver;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command rotates towards the goal.
 */
public class RotateWithVision extends Command {
    public double timeFactor = 1;
    
	VisionReceiver receiver;
	FrontBackMotors frontBack;
	LeftRightMotors leftRight;
    public RotateWithVision(FrontBackMotors frontBack, LeftRightMotors leftRight, VisionReceiver receiver) {
    	requires(frontBack);
    	requires(leftRight);
    	this.frontBack = frontBack;
    	this.leftRight = leftRight;
    	this.receiver = receiver;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			receiver.receive();
		} catch (IOException e) {
			// TODO: Figure out something reasonable here.
			e.printStackTrace();
		}
    	
    	double heading = receiver.getData().getHeading();
    	double speed = 1;
    	double time = heading * timeFactor;

    	if (heading > 0) {
    	    new RotateCommand(leftRight, frontBack, time, speed).start();
    	} else if (heading < 0) {
    	    new RotateCommand(leftRight, frontBack, time, -speed).start();
    	}
    	
    	this.cancel();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false; // TODO: When does this command actually finish?
    }

    // Called once after isFinished returns true
    protected void end() {
    	leftRight.stop();
    	frontBack.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
