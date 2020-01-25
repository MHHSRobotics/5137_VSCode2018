package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveForward extends Command {

	boolean isFinished;
	double speed;
	
	public DriveForward(double speed) {
		requires(Robot.driveBase);
		this.speed = speed;
		isFinished = false;
	} 
	
	protected void execute() {
		Robot.driveBase.driveStraight(speed);
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
	
}
