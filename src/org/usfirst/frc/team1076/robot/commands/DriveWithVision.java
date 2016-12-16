package org.usfirst.frc.team1076.robot.commands;

import java.io.IOException;
import java.net.SocketException;

import org.usfirst.frc.team1076.robot.subsystems.FrontBackMotors;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;
import org.usfirst.frc.team1076.robot.vision.VisionReceiver;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithVision extends Command {

	VisionReceiver receiver;
	FrontBackMotors frontBack;
	LeftRightMotors leftRight;
    public DriveWithVision() {
    	requires(frontBack);
    	requires(leftRight);
    	try {
			receiver = new VisionReceiver("10.10.76.2", 0); // TODO find the actual values for this
		} catch (SocketException e) {
			// TODO: This command should probably just stop if something errorful happens
			e.printStackTrace();
		} 
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
    	double range = receiver.getData().getRange();
    	double speed; // TODO: What equation should this use?
    	frontBack.setSpeed(1);
    	leftRight.setLeftSpeed(speed);
    	leftRight.setRightSpeed(-speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return dothisbeforemerging;
    }

    // Called once after isFinished returns true
    protected void end() {
    	dothisbeforemerging
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	dothisbeforemerging
    }
}
