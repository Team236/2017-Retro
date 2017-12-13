
package org.usfirst.frc.team236.robot;

import java.util.ArrayList;

import org.usfirst.frc.team236.robot.commands.auto.LeftGear;
import org.usfirst.frc.team236.robot.commands.auto.RightGear;
import org.usfirst.frc.team236.robot.commands.auto.StraightMotionMagic;
import org.usfirst.frc.team236.robot.subsystems.Climber;
import org.usfirst.frc.team236.robot.subsystems.Drive;
import org.usfirst.frc.team236.robot.subsystems.Garage;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import lib.AutoHandler;
import lib.commands.DoNothing;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// Declare OI
	public static OI oi;
	// Declare subsystems
	public static Climber climber;
	public static Garage garage;
	public static Drive drive;

	public static AutoHandler autoHandler;
	public static ArrayList<CommandGroup> autoModes;

	@Override
	public void robotInit() {
		// Create subsystems
		climber = new Climber();
		garage = new Garage();
		drive = new Drive();

		// Create oi
		oi = new OI();

		autoHandler = new AutoHandler(RobotMap.DIO_SWITCHES);
		// Generate auto modes
		// @formatter:off
		autoModes = new ArrayList<CommandGroup>();
		autoModes.add(new DoNothing());				// 000
		autoModes.add(new RightGear());				// 001
		autoModes.add(new StraightMotionMagic());	// 010
		autoModes.add(new DoNothing());				// 011
		autoModes.add(new LeftGear());				// 100
		autoModes.add(new DoNothing());				// 101
		autoModes.add(new DoNothing());				// 110
		autoModes.add(new DoNothing());				// 111
		// @formatter:on
	}

	@Override
	public void autonomousInit() {
		CommandGroup autoCommand = autoModes.get(autoHandler.getSelected());
		drive.zeroEncoders();

		if (autoCommand != null) {
			autoCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		drive.resetEncoders();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
