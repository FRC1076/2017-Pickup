package org.usfirst.frc.team1076.robot.subsystems;

import org.strongback.components.SpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * This subsystem represents the strafing motors on the robot, the motor at the front and the back of the robot.
 */
public class FrontBackMotors extends Subsystem {
	
	SpeedController frontMotor;
	SpeedController backMotor; 
	
	/**
	 * Constructs an instance of FrontBackMotors, passing it two motor controllers that
	 * represent the front and back motors.
	 * @param frontMotor
	 * @param backMotor
	 */
	public FrontBackMotors(SpeedController frontMotor, SpeedController backMotor) {
		this.frontMotor = frontMotor;
		this.backMotor = backMotor; 
	}

	/**
	 * Sets the speed of the front motor.
	 * @param speed a number in the range -1 to 1 inclusive.
	 */
	public void setFrontSpeed(double speed) {
		// Negative since the motor is reversed
		this.frontMotor.setSpeed(-speed);
	}

	/**
	 * Sets the speed of the back motor.
	 * @param speed a number in the range -1 to 1 inclusive.
	 */
	public void setBackSpeed(double speed) {
		this.backMotor.setSpeed(speed);
	}

	/**
	 * Sets the speed of both motors at the same time.
	 * @param speed a number in the range -1 to 1 inclusive.
	 */
	public void setSpeed(double speed) {
		setFrontSpeed(speed);
		setBackSpeed(speed);
	}

	/**
	 * Stops both motors by setting their speed to zero.
	 */
	public void stop() {
		setSpeed(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
