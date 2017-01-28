package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.Gamepad;
import org.usfirst.frc.team1076.robot.Gamepad.GamepadAxis;
import org.usfirst.frc.team1076.robot.subsystems.FrontBackMotors;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Controls the robot using a joy stick controller.
 * This command is intended to run continuously for the entire lifetime of the teleop mode.
 */
public class TeleopCommand extends Command {

	double maxSpeed = 0.5;
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
    	final double x = gamepad.getAxis(GamepadAxis.LeftX);
    	final double y = -gamepad.getAxis(GamepadAxis.LeftY);
    	// Counterclockwise ends up positive.
    	final double rot = gamepad.getAxis(GamepadAxis.RightX);
    	
    	// The robot wheel centers are 14.5" apart for the front and back,
    	// and 23" apart for the left and right.
    	// We'll use unit speed for the rotation on the left and right motors,
    	// and a scaled down speed for the front and back motors, so that they
    	// move at a speed proportional to their radius.
    	final double radiusScale = 14.5 / 23;

    	// To rotate clockwise, we want the following modification:
    	//   >
    	// ^   v
    	//   <
    	// Which means that frontSpeed should be increased, back decreased,
    	// left increased, and right decreased.
    	final double front = x + rot * radiusScale;
    	final double back = x - rot * radiusScale;
    	final double left = y + rot;
    	final double right = y - rot;
    	
    	// We don't want any motor to run faster than unit speed, so if anything
    	// is larger than the max speed we'll scale them down.
    	// We use the reciprocal of the max speed so that if for example maxSpeed
    	// is 0.5, then we'll get 2.0 and divide by 2.0.
    	final double norm = selectMaxAbs(new double[] {1/maxSpeed, front, back, left, right});
    	
    	frontBack.setFrontSpeed(front / norm);
    	frontBack.setBackSpeed(back / norm);
    	leftRight.setLeftSpeed(left / norm);
    	leftRight.setRightSpeed(right / norm);
    }
    
    private double selectMaxAbs(double[] items) {
    	assert items.length > 0;
    	double result = Math.abs(items[0]);
    	for (double item : items) {
    		result = Math.max(result, Math.abs(item));
    	}
    	return result;
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
    	end();
    }
}
