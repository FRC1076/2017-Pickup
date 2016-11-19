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
	
	public void setLeftSpeed(double speed) {
		this.leftMotor.set(speed);
	}
	
	public void setRightSpeed(double speed) {
		this.rightMotor.set(speed);
	}
	
	public void setSpeed(double speed) {
		setLeftSpeed(speed);
		setRightSpeed(speed);
	}
	
	public void stop() {
		setSpeed(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

