package org.usfirst.frc.team236.robot.commands.drive;

import org.usfirst.frc.team236.robot.Robot;
import org.usfirst.frc.team236.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import lib.pid.PID;

/**
 *
 */
public class Turn extends Command {

	private PID pid;
	private double distance;
	private double margin;
	private double error;

	/**
	 * Use PID control to turn the robot a desired angle
	 * @param _dist the distance to turn clockwise, in degrees
	 */
	public Turn(double _dist, double _margin) {
		requires(Robot.drive);
		pid = new PID(Robot.drive, Robot.drive, RobotMap.Drive.TURN_PARAMS);
		this.distance = _dist;
		this.margin = _margin;
	}

	protected void initialize() {
		pid.setSetpoint(distance);
		pid.enable();
	}

	protected void execute() {
		error = pid.getError();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Math.abs(error) < margin;
	}

	// Called once after isFinished returns true
	protected void end() {
		pid.disable();
		Robot.drive.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
