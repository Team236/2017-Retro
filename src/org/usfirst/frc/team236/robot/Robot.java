
package org.usfirst.frc.team236.robot;

import org.usfirst.frc.team236.robot.subsystems.Climber;
import org.usfirst.frc.team236.robot.subsystems.Drive;
import org.usfirst.frc.team236.robot.subsystems.Garage;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

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

	@Override
	public void robotInit() {
		// Create subsystems
		climber = new Climber();
		garage = new Garage();
		drive = new Drive();
	}
	
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
