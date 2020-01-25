package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;
import org.usfirst.frc.team5137.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

public class EncoderPivot90 extends Command {

	Encoder leftEncoder = RobotMap.leftEncoder;
	Encoder rightEncoder = RobotMap.rightEncoder;
	
	double distance;
	double speed;
	boolean isLeft;
	boolean isFinished;
	boolean isReset;
	
	public EncoderPivot90(double speed) {
		requires(Robot.driveBase);
		this.speed = speed;
		isLeft = false;
		isReset = false;
		isFinished = false;
	}
	
	public EncoderPivot90(double speed, boolean isLeft) {
		requires(Robot.driveBase);
		this.speed = speed;
		this.isLeft = isLeft;
		isReset = false;
		isFinished = false;
	}
	
	public void execute() {
		if (!isReset) {
			reset();
			isReset = true;
		}
		if (isLeft) {
			distance = 13;
			if (Math.abs(rightEncoder.getDistance()) < distance) {
				Robot.driveBase.pivot(-speed);
			} else isFinished = true;	
		} else {
			distance = 24;
			if (Math.abs(leftEncoder.getDistance()) < distance) {
				Robot.driveBase.pivot(speed);
			} else isFinished = true;
		}
	}
	
	protected void interrupted() {
		end();
	}
	
	protected boolean isFinished() {
		return isFinished;
	}
	
	protected void end() {
		leftEncoder.reset();
		rightEncoder.reset();
		Robot.driveBase.stop();
	}
	
	public void reset() {
		leftEncoder.reset();
		rightEncoder.reset();
		isFinished = false;
	}
	
	public void setDirection(boolean isLeft) {
		this.isLeft = isLeft;
	}
	
}
