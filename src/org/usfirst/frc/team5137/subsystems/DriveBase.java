package org.usfirst.frc.team5137.subsystems;

import org.usfirst.frc.team5137.commands.ArcadeDrive;
import org.usfirst.frc.team5137.robot.Robot;
import org.usfirst.frc.team5137.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveBase extends Subsystem {

	Spark leftDriveMotor = RobotMap.leftDriveMotor;
	Spark rightDriveMotor = RobotMap.rightDriveMotor;
	DifferentialDrive hotWheels = RobotMap.hotWheels;
	
	ADXRS450_Gyro gyro = RobotMap.gyro;

	private static final int IMG_WIDTH = 320;
	//private static final int IMG_HEIGHT = 240;
	public double centerX = 0.0;
	
	private double previousDriveSpeed = 0;
	
	public static double driveSpeed = 0; // for DisplayValues

	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDrive());
	}
	
	//	An algorithm developed by the fantastic Sarah C. Lincoln that adjusts the joysticks
	//	to run scaled to the deadZone
	public double adjustJoystickValue(double joystick, double deadZone) {
		double adjustedJoystick;
		if (Math.abs(joystick) < deadZone) {
			adjustedJoystick = 0;
		} else {
			adjustedJoystick = ((1 / (1 - deadZone)) * (joystick - deadZone));
		}
		return adjustedJoystick;
	}
	
	public void visionDrive() {
        double centerX;
        synchronized (Robot.imgLock) {
            centerX = Robot.centerX;
        }
        if (centerX > 1) {
        double turn = centerX - (IMG_WIDTH / 2);
        RobotMap.hotWheels.arcadeDrive( 0 , -turn * 0.005);
        System.out.println("centerX = " + centerX);
        System.out.println("turn = " + turn);
        }
    }
	
	/* 
	 * Arcade Drive is a form of driving...
	 * That allows one joystick on a controller to control both forwards/backwards and left and right (via SlideDrive)
	 * and delegates rotation to a different joystick
	 */
	public void rampArcadeDrive(Joystick jackBlack) {
		double driveJoystick = jackBlack.getRawAxis(1);
		double turnJoystick = jackBlack.getRawAxis(4);
		
		double newDriveSpeed;
		newDriveSpeed = accelerate(driveJoystick, previousDriveSpeed, .4, .05);
		driveSpeed = newDriveSpeed; // to print to SmartDashboard
		previousDriveSpeed = newDriveSpeed;
		
		hotWheels.arcadeDrive(newDriveSpeed, -turnJoystick);
	}

	public double accelerate(double joystickValue, double previousSpeed, double minSpeed, double incValue) {
		int delay = 25;
		double newSpeed;
		
		// effectively a deadzone with range +-minValue
		if (Math.abs(joystickValue) > minSpeed && Math.abs(previousSpeed) < minSpeed) {
			newSpeed = Math.signum(joystickValue) * minSpeed;
		} else {
			newSpeed = previousSpeed;
		}
		
		if (previousSpeed < joystickValue) newSpeed += incValue;
		else if (previousSpeed > joystickValue) newSpeed -= incValue;
		else newSpeed = previousSpeed; // necessary??
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {

		}
		
		return newSpeed;
	}
	
	public void driveStraight(double speed) {
		hotWheels.arcadeDrive(-speed, 0);
	}
	
	public void gyroDriveStraight(double speed) {
		hotWheels.arcadeDrive(-speed, -gyro.getRate()); // maybe switch the negative?
	}
	
	public void pivot(double speed) {
		leftDriveMotor.set(-speed);
		rightDriveMotor.set(-speed);
	}
	
	public void stop() {
		hotWheels.arcadeDrive(0, 0);
	}

}
