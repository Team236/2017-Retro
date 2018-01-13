package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Garage extends Subsystem {

	private DoubleSolenoid vertical, grasper;
	private Relay flashlight;

	public Garage() {
		vertical = new DoubleSolenoid(RobotMap.Garage.SOL_VERTICAL_FORWARD, RobotMap.Garage.SOL_VERTICAL_REVERSE);
		grasper = new DoubleSolenoid(RobotMap.Garage.SOL_GRASPER_FORWARD, RobotMap.Garage.SOL_GRASPER_REVERSE);

		flashlight = new Relay(RobotMap.Garage.RELAY_FLASHLIGHT);
		flashlight.setDirection(Relay.Direction.kForward);
	}
	
	public void raise() {
		vertical.set(Value.kForward);
	}
	
	public void lower() {
		vertical.set(Value.kReverse);
	}
	
	public void grasp() {
		grasper.set(Value.kForward);
	}
	
	public void release() {
		grasper.set(Value.kReverse);
	}
	
	public void lightOn() {
		flashlight.set(Relay.Value.kOn);
	}

	public void lightOff() {
		flashlight.set(Relay.Value.kOff);
	}

	// No default command
    public void initDefaultCommand() {
    }
}

