package org.usfirst.frc.team1076.robot.commands;

import org.strongback.command.CommandGroup;
import org.strongback.components.Motor;

public class AutonomousCommand extends CommandGroup {
	Motor leftmotor;
	Motor rightmotor;

	public AutonomousCommand(Motor leftmotor, Motor rightmotor) {
		this.leftmotor = leftmotor;
		this.rightmotor = rightmotor;
		
		// drive 8ft
		sequentialy(
		       new ForwardCommand(rightmotor, leftmotor),
			   new RotateCommand(leftmotor, rightmotor, 5.0, 1),
			   new ForwardWithVision(rightmotor, rightmotor),
		
	    leftmotor.setSpeed(1);
		rightmotor.setSpeed(1);
		
	}
}
