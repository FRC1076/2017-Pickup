package org.usfirst.frc.team1076.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LeftRightMotors extends Subsystem {
    
	SpeedController leftMotor;
	SpeedController rightMotor;
	
	public LeftRightMotors(SpeedController leftMotor, SpeedController rightMotor) {
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;		
	}
	/**
	 * sets the speed of the left motor
	 * @param speed
	 */
	public void setLeftSpeed(double speed) {
		this.leftMotor.set(speed);
	}
	/**
	 * sets the speed of the right motor
	 * @param speed
	 */
	public void setRightSpeed(double speed) {
		this.rightMotor.set(speed);
	}
	/**
	 * sets the speed of both left and right motors at the same time, so the robot can move forwards or backwards
	 * @param speed
	 */
	public void setSpeed(double speed) {
		setLeftSpeed(speed);
		setRightSpeed(speed);
	}
	/**
	 * stops left and right motors
	 */
	public void stop() {
		setSpeed(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

