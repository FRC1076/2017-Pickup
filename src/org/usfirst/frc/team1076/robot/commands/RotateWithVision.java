package org.usfirst.frc.team1076.robot.commands;

import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;
import org.usfirst.frc.team1076.robot.vision.VisionReceiver;
import org.usfirst.frc.team1076.robot.vision.VisionData;
import org.usfirst.frc.team1076.robot.vision.VisionData.VisionStatus;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command rotates towards the goal.
 * This command only runs once.
 */
public class RotateWithVision extends Command {
    public double timeFactor = 1;

    VisionReceiver receiver;
    LeftRightMotors leftRight;
    
    public RotateWithVision(LeftRightMotors leftRight, VisionReceiver receiver) {
        requires(leftRight);
        this.leftRight = leftRight;
        this.receiver = receiver;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        receiver.receive();

        VisionData data = receiver.getData();
        double heading = data.getHeading();
        VisionStatus status = data.getStatus();
        double speed = 1;
        double time = heading * timeFactor;

        if (status.equals(VisionStatus.ERROR) && data.getErrorCount() > 10) {
            return;
        }

        if (heading > 0) {
            new RotateCommand(leftRight, time, speed).start();
        } else if (heading < 0) {
            new RotateCommand(leftRight, time, -speed).start();
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() { }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() { }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}
