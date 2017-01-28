
package org.usfirst.frc.team1076.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.net.SocketException;

import org.usfirst.frc.team1076.robot.commands.AutoCommandGroup;
import org.usfirst.frc.team1076.robot.commands.CancelCommand;
import org.usfirst.frc.team1076.robot.commands.SonarTrigger;
import org.usfirst.frc.team1076.robot.subsystems.DoorPneumatic;
import org.usfirst.frc.team1076.robot.commands.TeleopCommand;
import org.usfirst.frc.team1076.robot.subsystems.FrontBackMotors;
import org.usfirst.frc.team1076.robot.subsystems.LeftRightMotors;
import org.usfirst.frc.team1076.robot.vision.VisionReceiver;

import com.ctre.CANTalon;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	final double DRIVE_SPEED_DEFAULT = 0.5;
	final double DRIVE_TIME_DEFAULT = 1.0;
	final double LEFT_FACTOR_DEFAULT = 0.98;
	final double TIME_FACTOR_DEFAULT = 0.04;
	final double TURN_SPEED_DEFAULT = 0.2;

    public static final String IP = "0.0.0.0";
    public static final int VISION_PORT = 5880;
    public static final int SONAR_PORT = 5881;

	public static OI oi;
	Gamepad gamepad = new Gamepad(0);
	CANTalon leftMotor = new CANTalon(2);
	CANTalon rightMotor = new CANTalon(0);
	CANTalon frontMotor = new CANTalon(3);
	CANTalon backMotor = new CANTalon(1);
	LeftRightMotors leftRight = new LeftRightMotors(leftMotor, rightMotor);
	FrontBackMotors frontBack = new FrontBackMotors(frontMotor, backMotor);
	TeleopCommand teleopCommand = new TeleopCommand(gamepad, frontBack, leftRight);
    
    Command autonomousCommand;
    Compressor compressor = new Compressor(0);
    DoorPneumatic door;
    VisionReceiver visionReceiver;
    SonarReceiver sonarReceiver;
    SonarTrigger sonarTrigger;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        door = new DoorPneumatic(new Solenoid(0));
        System.out.println(door);
		oi = new OI(door);
		gamepad = new Gamepad(0);
        compressor.start();
        SmarterDashboard.putDefaultNumber("Speed", DRIVE_SPEED_DEFAULT);
        SmarterDashboard.putDefaultNumber("Time", DRIVE_TIME_DEFAULT);
        SmarterDashboard.putDefaultNumber("Left Factor", LEFT_FACTOR_DEFAULT);
        SmarterDashboard.putDefaultNumber("Time Factor", TIME_FACTOR_DEFAULT);
        SmarterDashboard.putDefaultNumber("Turn Speed", TURN_SPEED_DEFAULT);
        
        try {
            visionReceiver = new VisionReceiver(IP, VISION_PORT);
            sonarReceiver = new SonarReceiver(IP, SONAR_PORT);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
	
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit() {

    }
	
    int dbgCounter = 0;
	public void disabledPeriodic() {
		if (dbgCounter++ % 50 == 0) {
			visionReceiver.receive();
			System.out.println(
					"status: " + visionReceiver.getData().getStatus() +
					", heading: " + visionReceiver.getData().getHeading() +
					", range: " + visionReceiver.getData().getRange() +
					", errors: " + visionReceiver.getData().getErrorCount());
		}
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmarterDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	leftRight.leftFactor = SmarterDashboard.getNumber("Left Factor", LEFT_FACTOR_DEFAULT);
    	double speed = SmarterDashboard.getNumber("Speed", DRIVE_SPEED_DEFAULT);
    	double time = SmarterDashboard.getNumber("Time", DRIVE_TIME_DEFAULT);
    	double turnFactor = SmarterDashboard.getNumber("Time Factor", TIME_FACTOR_DEFAULT);
    	double turnSpeed = SmarterDashboard.getNumber("Turn Speed", TURN_SPEED_DEFAULT);
    	autonomousCommand = new AutoCommandGroup(frontBack, leftRight, visionReceiver,
    			speed, time, turnFactor, turnSpeed);
    	CancelCommand cancel = new CancelCommand(new Command[] { autonomousCommand });
    	sonarTrigger = new SonarTrigger(10, sonarReceiver, cancel);
    	sonarTrigger.start();
    	
		/* String autoSelected = SmarterDashboard.getString("Auto Selector", "Default");
		switch(autoSelected) {
		case "My Auto":
			autonomousCommand = new MyAutoCommand();
			break;
		case "Default Auto":
		default:
			autonomousCommand = new ExampleCommand();
			break;
		} */
    	
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) { autonomousCommand.cancel(); }
        if (sonarTrigger != null) {	sonarTrigger.cancel(); }
        teleopCommand.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    	LiveWindow.run();
    }
}
