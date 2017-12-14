package org.usfirst.frc.team236.robot.commands.drive;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BetterDriveWithJoysticks extends Command {

	public BetterDriveWithJoysticks() {
		requires(Robot.drive);
	}

	protected void initialize() {
		Robot.drive.goPercentVbus();
	}

	protected void execute() {
		double left = Robot.oi.leftStick.y();
		double right = Robot.oi.rightStick.y();

		Robot.drive.setLeftSpeed(Math.pow(left, 3));
		Robot.drive.setRightSpeed(Math.pow(right, 3));
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		Robot.drive.stop();
	}

	protected void interrupted() {
		end();
	}
}
