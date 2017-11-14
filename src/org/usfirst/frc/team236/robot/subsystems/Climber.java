package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.Climb;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	private SpeedController motor;

	public Climber() {
		motor = new VictorSP(RobotMap.Climber.PWM_CLIMBER);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new Climb());
	}

	public void setSpeed(double speed) {
		motor.set(speed);
	}

	public void stop() {
		setSpeed(0);
	}
}
