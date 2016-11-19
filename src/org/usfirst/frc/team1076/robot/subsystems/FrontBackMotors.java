package org.usfirst.frc.team1076.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FrontBackMotors extends Subsystem {
	
	SpeedController frontMotor;
	SpeedController backMotor; 
	
	public FrontBackMotors(SpeedController frontMotor, SpeedController backMotor) {
		this.frontMotor = frontMotor;
		this.backMotor = backMotor; 
	}

	public void setFrontSpeed(double speed) {
		this.frontMotor.set(speed);
	}

	public void setBackSpeed(double speed) {
		this.backMotor.set(speed);
	}

	public void setSpeed(double speed) {
		setFrontSpeed(speed);
		setBackSpeed(speed);
	}

	public void stop() {
		setSpeed(0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
