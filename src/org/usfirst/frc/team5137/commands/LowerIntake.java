package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/*
 * Lowers the intake subsystem. Works in auto and teleop.
 * If in auto, tell it how long (we never hooked up the 
 * encoders inside the seat motors).
 */
public class LowerIntake extends Command {

	Timer timer;
	
	double howLong;
	boolean isTimed;
	boolean timerRunning;
	boolean isFinished;
	
	// Constructor for teleop
	public LowerIntake() {
		requires(Robot.intakeNoun);
		isTimed = false;
		isFinished = false;
	} 
	
	// Constructor for autonomous
	public LowerIntake(double howLong) {
		requires(Robot.intakeNoun);
		timer = new Timer();
		this.howLong = howLong;
		isTimed = true;
		timerRunning = false;
		isFinished = false;
	}
	
	protected void execute() {
		if (isTimed) {
			if (!timerRunning) {
				timer.reset();
				timer.start();
				timerRunning = true;
			}
			if (timer.get() < howLong) {
				Robot.intakeNoun.lowerIntake();
			} else {
				isFinished = true;
			}
		} else {
			Robot.intakeNoun.lowerIntake();
		}
	}
	
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		Robot.intakeNoun.stop();
	}
	
	protected boolean isFinished() {
		return isFinished;
	}
	
}
