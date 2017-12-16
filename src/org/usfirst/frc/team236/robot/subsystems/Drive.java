package org.usfirst.frc.team236.robot.subsystems;

import org.usfirst.frc.team236.robot.RobotMap;
import org.usfirst.frc.team236.robot.commands.drive.DriveWithJoysticks;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;
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

	public CANTalon leftMaster, leftSlave;
	public CANTalon rightMaster, rightSlave;
	
	private DoubleSolenoid shifter;
	public Gear gear;
	
	public AHRS navx;

	public Drive() {
		leftMaster = new CANTalon(RobotMap.Drive.ID_LEFT_FRONT);
		rightMaster = new CANTalon(RobotMap.Drive.ID_RIGHT_FRONT);
		leftSlave = new CANTalon(RobotMap.Drive.ID_LEFT_REAR);
		rightSlave = new CANTalon(RobotMap.Drive.ID_RIGHT_REAR);
		
		leftSlave.changeControlMode(TalonControlMode.Follower);
		rightSlave.changeControlMode(TalonControlMode.Follower);
		
		leftSlave.set(leftMaster.getDeviceID());
		rightSlave.set(rightMaster.getDeviceID());
		
		shifter = new DoubleSolenoid(RobotMap.Drive.SOL_FORWARD, RobotMap.Drive.SOL_REVERSE);
		
		navx = new AHRS(SPI.Port.kMXP);
		
		configDirection();
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
	
	public void goMotionMagic() {
		leftMaster.changeControlMode(TalonControlMode.MotionMagic);
		rightMaster.changeControlMode(TalonControlMode.MotionMagic);
	}
	
	public void goPercentVbus() {
		leftMaster.changeControlMode(TalonControlMode.PercentVbus);
		rightMaster.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public void setLeftSpeed(double speed) {
		leftMaster.set(speed);
	}
	
	public void setRightSpeed(double speed) {
		rightMaster.set(-speed);
	}
	
	public void setSpeeds(double left, double right) {
		this.setLeftSpeed(left);
		this.setRightSpeed(right);
	}

	public void zeroEncoders() {
		leftMaster.setPosition(0);
		rightMaster.setPosition(0);
	}
	
	public void stop() {
		this.setSpeeds(0, 0);
	}
	
	private void configDirection() {
		leftMaster.reverseSensor(true);
		rightMaster.reverseSensor(true);
		
		rightMaster.reverseSensor(false);
		leftMaster.reverseSensor(false);
	}
	
	private void configPID() {
		leftMaster.setP(RobotMap.Drive.PID.P);
		leftMaster.setI(RobotMap.Drive.PID.I);
		leftMaster.setD(RobotMap.Drive.PID.D);

		rightMaster.setP(RobotMap.Drive.PID.P);
		rightMaster.setI(RobotMap.Drive.PID.I);
		rightMaster.setD(RobotMap.Drive.PID.D);
	}
	
	public void configMotionMagic(double cruise, double accel) {
		leftMaster.setMotionMagicCruiseVelocity(cruise);
		leftMaster.setMotionMagicAcceleration(accel);

		rightMaster.setMotionMagicCruiseVelocity(cruise);
		rightMaster.setMotionMagicAcceleration(accel);
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
