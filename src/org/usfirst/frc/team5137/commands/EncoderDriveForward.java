package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;
import org.usfirst.frc.team5137.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Command;

/*
 * Drives forward a given distance and speed using the driveBase's 
 * left motor encoder. Could've used the right motor encoder, 
 * doesn't matter. Not sure how to drive backwards.
 */
public class EncoderDriveForward extends Command implements RepeatsInTeleop {

	Encoder leftEncoder = RobotMap.leftEncoder;
	
	double distance;
	double speed;
	boolean isFinished;
	boolean isReset;
	
	public EncoderDriveForward(double distance, double speed) {
		requires(Robot.driveBase);
		this.distance = distance;
		this.speed = speed;
		isFinished = false;
		isReset = false;
		leftEncoder.reset();
	}
	
	public void execute() {
		if (!isReset) {
			reset();
			isReset = true;
		}
		if (Math.abs(leftEncoder.getDistance()) < distance) {
			Robot.driveBase.driveStraight(speed);
		} else isFinished = true;
	}
	
	protected void interrupted() {
		end();
	}
	
	protected boolean isFinished() {
		return isFinished;
	}
	
	protected void end() {
		leftEncoder.reset();
		Robot.driveBase.stop();
	}
	
	public void reset() {
		leftEncoder.reset();
		isFinished = false;
	}
	
}
