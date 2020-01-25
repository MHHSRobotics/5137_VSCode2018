package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/*
 * Performs the action of intaking. Works in autonomous or teleop.
 * If you use it in autonomous, you have to tell it how long to go. 
 */
public class IntakeVerb extends Command {

	Timer timer;
	
	double howLong;
	boolean isTimed;
	boolean timerRunning;
	boolean isFinished;
	
	// Constructor for teleop
	public IntakeVerb() {
		requires(Robot.intakeNoun);
		isTimed = false;
		isFinished = false;
	} 
	
	// Constructor for autonomous
	public IntakeVerb(double howLong) {
		requires(Robot.intakeNoun);
		timer = new Timer();
		this.howLong = howLong;
		isTimed = true;
		timerRunning = false;
		isFinished = false;
	}
	
	protected void execute() {
		if (isTimed) {
			// Starts the timer if it hasn't been started yet.
			if (!timerRunning) {
				timer.reset();
				timer.start();
				timerRunning = true;
			}
			if (timer.get() < howLong) {
				Robot.intakeNoun.intake();
			} else {
				isFinished = true;
			}
		} else {
			Robot.intakeNoun.intake();
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
