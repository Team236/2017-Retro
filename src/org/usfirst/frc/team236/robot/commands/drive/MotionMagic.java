package org.usfirst.frc.team236.robot.commands.drive;

import org.usfirst.frc.team236.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MotionMagic extends Command {

	public double distance;

	public MotionMagic(double _distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drive);
		this.distance = _distance;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.drive.leftMaster.set(ControlMode.MotionMagic, distance);
		Robot.drive.rightMaster.set(ControlMode.MotionMagic, -distance);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Motion magiccing");
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
