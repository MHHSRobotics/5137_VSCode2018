package org.usfirst.frc.team5137.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class Wait extends Command {
	
	Timer timer;
	
	boolean isTimed;
	boolean timerRunning;
	double howLong;
	boolean isFinished;
	
	public Wait() {
		isTimed = false;
		isFinished = false;
	}
	
	public Wait(double howLong) {
		isTimed = true;
		timer = new Timer();
		timerRunning = false;
		this.howLong = howLong;
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
				;
			}
			else {
				isFinished = true;
			}
		} else {
			;
		}
	}
	
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		;
	}

	protected boolean isFinished() {
		return isFinished;
	}

}
