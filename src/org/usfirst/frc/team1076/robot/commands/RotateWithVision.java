package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.subsystems.FrontBackMotors;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;
import org.usfirst.frc.team1076.robot.vision.VisionReceiver;
import org.usfirst.frc.team1076.robot.vision.VisionData;
import org.usfirst.frc.team1076.robot.vision.VisionData.VisionStatus;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command rotates towards the goal.
 * This command only runs once.
 */
public class RotateWithVision extends Command {
    public double timeFactor = 1;
    public double speed = 0.3;

    VisionReceiver receiver;
    FrontBackMotors frontBack;
    LeftRightMotors leftRight;
    RotateCommand commandDelegate;
    
    public RotateWithVision(FrontBackMotors frontBack, LeftRightMotors leftRight, VisionReceiver receiver) {
        requires(frontBack);
        requires(leftRight);
        this.frontBack = frontBack;
        this.leftRight = leftRight;
        this.receiver = receiver;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        receiver.receive();

        VisionData data = receiver.getData();
        double heading = data.getHeading();
        VisionStatus status = data.getStatus();
        double time = Math.abs(heading * timeFactor);
        
        if (status.equals(VisionStatus.ERROR) && data.getErrorCount() > 10) {
        	System.out.println("Failed with " + data.getErrorCount() + " errors in a row.");
            return;
        }

        if (heading > 0) {
            commandDelegate = new RotateCommand(leftRight, frontBack, time, speed);
        } else if (heading < 0) {
            commandDelegate = new RotateCommand(leftRight, frontBack, time, -speed);
        }
        
        if (commandDelegate != null) {
        	commandDelegate.initialize();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (commandDelegate != null) {
    		commandDelegate.execute();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (commandDelegate != null) {
    		return commandDelegate.isFinished();
    	} else {
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("End!");
    	if (commandDelegate != null) {
    		commandDelegate.end();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	if (commandDelegate != null) {
    		commandDelegate.interrupted();
    	}
    }
}
