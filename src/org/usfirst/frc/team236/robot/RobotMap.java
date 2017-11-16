package org.usfirst.frc.team236.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Climber {
		public static final int PWM_CLIMBER = 7;
	}
	
	public static class Garage {
		public static final int SOL_GRASPER_FORWARD = 2;
		public static final int SOL_GRASPER_REVERSE = 3;

		public static final int SOL_VERTICAL_FORWARD = 4;
		public static final int SOL_VERTICAL_REVERSE = 5;
	}
}
