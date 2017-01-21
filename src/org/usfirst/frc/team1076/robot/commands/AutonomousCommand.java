package org.usfirst.frc.team1076.robot.commands;

import org.strongback.command.Command;
import org.strongback.components.Motor;

public class AutonomousCommand extends Command {
	Motor leftmotor;
	Motor rightmotor;

	public AutonomousCommand(Motor leftmotor, Motor rightmotor) {
		this.leftmotor = leftmotor;
		this.rightmotor = rightmotor;
	}
	
	@Override
	public boolean execute() {
		leftmotor.setSpeed(1);
		rightmotor.setSpeed(1);
		return false;
	}

}
