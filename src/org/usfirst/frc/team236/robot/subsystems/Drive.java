package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.drive.DriveWithJoysticks;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import lib.pid.PIDOutput;
import lib.pid.PIDSource;

/**
 *
 */
public class Drive extends Subsystem implements PIDSource, PIDOutput{

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public TalonSRX leftMaster, leftSlave;
	public TalonSRX rightMaster, rightSlave;
	
	private DoubleSolenoid shifter;
	public Gear gear;
	
	public AHRS navx;

	public Drive() {
		leftMaster = new TalonSRX(RobotMap.Drive.ID_LEFT_FRONT);
		rightMaster = new TalonSRX(RobotMap.Drive.ID_RIGHT_FRONT);
		leftSlave = new TalonSRX(RobotMap.Drive.ID_LEFT_REAR);
		rightSlave = new TalonSRX(RobotMap.Drive.ID_RIGHT_REAR);
		
		leftSlave.set(ControlMode.Follower, leftMaster.getDeviceID());
		rightSlave.set(ControlMode.Follower, rightMaster.getDeviceID());
		
		shifter = new DoubleSolenoid(RobotMap.Drive.SOL_FORWARD, RobotMap.Drive.SOL_REVERSE);
		
		navx = new AHRS(SPI.Port.kMXP);
		
		//configDirection();
		configPID();
		configMotionMagic(RobotMap.Drive.CRUISE_VELOCITY, RobotMap.Drive.MAX_ACCEL);
	}
	
	public enum Gear {
		HIGH, LOW
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoysticks());
	}

	public void shiftUp() {
		this.shifter.set(Value.kReverse);
		gear = Gear.HIGH;
	}
	
	public void shiftDown() {
		this.shifter.set(Value.kForward);
		gear = Gear.LOW;
	}
	
	public Gear getGear() {
		return gear;
	}
	
	/**
	 * Set left speed from -1 to 1.
	 */
	public void setLeftSpeed(double speed) {
		leftMaster.set(ControlMode.PercentOutput, speed);
	}
	
	/**
	 * Set right speed from -1 to 1.
	 */
	public void setRightSpeed(double speed) {
		rightMaster.set(ControlMode.PercentOutput, -speed);
	}
	
	/**
	 * Set speeds from -1 to 1.
	 */
	public void setSpeeds(double left, double right) {
		this.setLeftSpeed(left);
		this.setRightSpeed(right);
	}

	/**
	 * Set encoders to 0
	 */
	public void zeroEncoders() {
		leftMaster.setSelectedSensorPosition(0,0,0);
		rightMaster.setSelectedSensorPosition(0,0,0);
	}
	
	/**
	 * Stop motors.
	 */
	public void stop() {
		this.setSpeeds(0, 0);
	}
	
	/**
	 * Set up default direction settings.
	 */
	private void configDirection() {
		/*
		leftMaster.reverseSensor(true);
		rightMaster.reverseSensor(true);
		
		rightMaster.reverseSensor(false);
		leftMaster.reverseSensor(false);
		*/
	}
	
	/**
	 * Configure default PID parameters
	 */
	private void configPID() {
		leftMaster.config_kP(0, RobotMap.Drive.PID.P, 0);
		leftMaster.config_kI(0, RobotMap.Drive.PID.I, 0);
		leftMaster.config_kD(0, RobotMap.Drive.PID.D, 0);

		rightMaster.config_kP(0, RobotMap.Drive.PID.P, 0);
		rightMaster.config_kI(0, RobotMap.Drive.PID.I, 0);
		rightMaster.config_kD(0, RobotMap.Drive.PID.D, 0);
	}
	
	/**
	 * Set up default motion magic parameters
	 * @param cruise Cruise velocity
	 * @param accel Maximum acceleration
	 */
	public void configMotionMagic(double cruise, double accel) {
		leftMaster.configMotionCruiseVelocity((int) cruise, 0);
		leftMaster.configMotionAcceleration((int) accel, 0);

		rightMaster.configMotionCruiseVelocity((int) cruise, 0);
		rightMaster.configMotionAcceleration((int) accel, 0);
	}

	@Override
	public void pidSet(double speed) {
		setLeftSpeed(speed);
		setRightSpeed(-speed);
	}

	@Override
	public double pidGet() {
		return navx.getAngle();
	}
}
