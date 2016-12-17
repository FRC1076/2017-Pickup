package org.usfirst.frc.team1076.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Claw extends Subsystem {
	
	SpeedController clawMotor;
	
	public Claw(SpeedController clawMotor) {
		this.clawMotor = clawMotor;
	}
	
	public void setMotorSpeed(double speed) {
		this.clawMotor.set(speed);
	}
    
	public void intakeBall() {
		setMotorSpeed(1);
	}
	
	public void expellBall() {
		setMotorSpeed(-1);
	}
	
	public void stop() {
		setMotorSpeed(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

