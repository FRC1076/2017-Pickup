package org.usfirst.frc.team1076.robot.subsystems;

import org.strongback.components.Motor;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem represents the motors of the robot.
 * Note that this class assumes that all motors are forward facing.
 */
public class LeftRightMotors extends Subsystem {
    
	public double leftFactor = 1.0;
	Motor leftMotor;
	Motor rightMotor;
	
	/**
	 * Constructs on instance of LeftRightMotors, passing it two motor controllers
	 * that represent the left and right motors.
	 * @param leftMotor
	 * @param rightMotor
	 */
	public LeftRightMotors(Motor left, Motor right) {
		this.leftMotor = left;
		this.rightMotor = right;
	}

	/**
	 * Sets the speed of the left motor.
	 * @param speed in the range -1 to 1 inclusive.
	 */
	public void setLeftSpeed(double speed) {
		this.leftMotor.setSpeed(speed * leftFactor);
	}

	/**
	 * Sets the speed of the right motor.
	 * @param speed in the range -1 to 1 inclusive.
	 */
	public void setRightSpeed(double speed) {
		this.rightMotor.setSpeed(speed);
	}
	
	/**
	 * Sets the speed of both left and right motors at the same time, so the robot can move
	 * forwards or backwards.
	 * @param speed in the range -1 to 1 inclusive.
	 */
	public void setSpeed(double speed) {
		setLeftSpeed(speed);
		setRightSpeed(speed);
	}
	
	/**
	 * Stops left and right motors.
	 */
	public void stop() {
		setSpeed(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

