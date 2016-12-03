package org.usfirst.frc.team1076.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**This subsystem /controls the door.
 *
 */
public class DoorPneumatic extends Subsystem {
    
	Compressor compressor;
	Solenoid solenoid;
	public DoorPneumatic(Compressor compressor, Solenoid solenoid){
		this.compressor = compressor;
		this.solenoid = solenoid;
		
		
	}
	public void open() {
		solenoid.set(true);
	}
	
	public void close(){
		solenoid.set(false);
	}
	
	public boolean value(){
		return solenoid.get();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

