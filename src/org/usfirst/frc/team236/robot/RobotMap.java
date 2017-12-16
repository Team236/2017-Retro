package org.usfirst.frc.team236.robot;

import lib.pid.PIDParameters;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static int[] DIO_SWITCHES = { 0, 1, 2 };

	public static class Climber {
		public static final int PWM_CLIMBER = 7;
	}

	public static class Garage {
		public static final int SOL_GRASPER_FORWARD = 2;
		public static final int SOL_GRASPER_REVERSE = 3;

		public static final int SOL_VERTICAL_FORWARD = 4;
		public static final int SOL_VERTICAL_REVERSE = 5;
		
		public static final int RELAY_FLASHLIGHT = 0;
	}

	public static class Drive {
		public static final int ID_LEFT_FRONT = 1;
		public static final int ID_LEFT_REAR = 3;
		public static final int ID_RIGHT_FRONT = 4;
		public static final int ID_RIGHT_REAR = 2;

		public static final int SOL_FORWARD = 0;
		public static final int SOL_REVERSE = 1;

		public static final double DIAMETER = 3.92;
		public static final double CIRCUMFERENCE = DIAMETER * Math.PI;
		public static final double PULSE_PER_ROTATION = 128;
		public static final double DISTANCE_PER_PULSE = CIRCUMFERENCE / PULSE_PER_ROTATION;
		
		public static final double CRUISE_VELOCITY = 0;
		public static final double MAX_ACCEL = 0;

		public static class PID {
			public static final double P = 0.0;
			public static final double I = 0.0;
			public static final double D = 0.0;
			public static final double F = 0.0;
		}

		public static PIDParameters TURN_PARAMS = new PIDParameters(0.015, 0.007, 0.000, 1 / 100.0);
	}
}
