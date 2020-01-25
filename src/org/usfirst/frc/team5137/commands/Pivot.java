package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Pivot extends Command {

	Timer timer;
	
	boolean isTimed;
	boolean timerRunning;
	double howLong;
	double speed;
	boolean isLeft;
	boolean isFinished;
	
	public Pivot(double speed) {
		isTimed = false;
		this.speed = speed;
		isFinished = false;
	}
	
	public Pivot(double howLong, double speed) {
		requires(Robot.driveBase);
		timer = new Timer();
		timerRunning = false;
		this.howLong = howLong;
		this.speed = speed;
		isFinished = false;
	}
	
	public void execute() {
		if (isTimed) {
			if (!timerRunning) {
				timer.reset();
				timer.start();
				timerRunning = true;
			}
			if (timer.get() < howLong) {
				if (isLeft) Robot.driveBase.pivot(-speed);
				else Robot.driveBase.pivot(speed);
			}
			else {
				isFinished = true;
			}
		} else {
			if (isLeft) Robot.driveBase.pivot(-speed);
			else Robot.driveBase.pivot(speed);
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
