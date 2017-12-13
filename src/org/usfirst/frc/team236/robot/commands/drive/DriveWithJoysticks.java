package org.usfirst.frc.team236.robot.commands.drive;

import org.usfirst.frc.team236.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithJoysticks extends Command {

	public DriveWithJoysticks() {
		requires(Robot.drive);
	}

	protected void initialize() {
		Robot.drive.goPercentVbus();
	}

	protected void execute() {
		Robot.drive.setLeftSpeed(Robot.oi.leftStick.y());
		Robot.drive.setRightSpeed(Robot.oi.rightStick.y());
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
