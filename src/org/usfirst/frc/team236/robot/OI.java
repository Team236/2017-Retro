package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.commands.Climb;
import org.usfirst.frc.team236.robot.commands.garage.Grasp;
import org.usfirst.frc.team236.robot.commands.garage.Lower;
import org.usfirst.frc.team236.robot.commands.garage.Raise;
import org.usfirst.frc.team236.robot.commands.garage.Release;

import lib.oi.LogitechF310;
import lib.oi.Thrustmaster;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public LogitechF310 controller;
	public Thrustmaster leftStick, rightStick;
	
	public OI() {
		controller = new LogitechF310(ControlMap.PORT_CONTROLLER);
		leftStick = new Thrustmaster(ControlMap.PORT_LEFT);
		rightStick = new Thrustmaster(ControlMap.PORT_RIGHT);
		
		controller.lb.whileHeld(new Climb());
		
		controller.y.whenPressed(new Raise());
		controller.a.whenPressed(new Lower());
		
		controller.x.whenPressed(new Grasp());
		controller.b.whenPressed(new Release());
	}
}
