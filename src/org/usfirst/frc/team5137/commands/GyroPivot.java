package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;
import org.usfirst.frc.team5137.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Command;

public class GyroPivot extends Command {
	
	ADXRS450_Gyro gyro;
	
	double degrees;
	double speed;
	boolean isLeft;
	boolean isFinished;
	
	public GyroPivot(double degrees, double speed) {
		requires(Robot.driveBase);
		gyro = RobotMap.gyro;
		this.degrees = degrees;
		this.speed = speed;
		isFinished = false;
	}
	
	public void execute() {
		double leeway = 2;
		if (gyro.getAngle() < degrees - leeway) {
			Robot.driveBase.pivot(speed);
		} else if (gyro.getAngle() > degrees + leeway) {
			Robot.driveBase.pivot(-speed);
		} else {
			isFinished = true;
		}
	}
	
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		Robot.driveBase.stop();
	}

	protected boolean isFinished() {
		return isFinished;
	}
	
	public void setDirection(boolean isLeft) {
		this.isLeft = isLeft;
	}
}
