package org.usfirst.frc.team1076.robot.commands;

import org.strongback.command.Command;
import org.strongback.components.Motor;
import org.strongback.drive.TankDrive;

/**
 *
 */
public class RotateCommand extends Command {

    TankDrive tank;
	double targetTime;
	double time;
	double speed;
	
	/**
	 * Creates a RotateCommand giving it the subsystems and angle it requires.
	 * @param left
	 * @param right
	 * @param targetTime measured in seconds.
	 * @param speed in the range of -1 to 1. Positive numbers rotate clockwise, negative numbers rotate counterclockwise.
	 */
    public RotateCommand(Motor left, Motor right, double targetTime, double speed) {
        super(left, right);
    	this.tank = new TankDrive(left, right);
    	this.targetTime = targetTime;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    	this.time = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    public boolean execute() {
    	time += 1/50.0;
    	tank.arcade(0, speed);
    	return time >= targetTime;
    }
}
