package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/* 
 * Raises the intake subsystem. Works in auto and teleop.
 * If auto, tell it how long (we never hooked up seat
 * motor encoders).
 */
public class RaiseIntake extends Command {

	Timer timer;
	
	double howLong;
	boolean isTimed;
	boolean timerRunning;
	boolean isFinished;
	
	public RaiseIntake() {
		requires(Robot.intakeNoun);
		isTimed = false;
		isFinished = false;
	} 
	
	public RaiseIntake(double howLong) {
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
				Robot.intakeNoun.raiseIntake();
			} else {
				isFinished = true;
			}
		} else {
			Robot.intakeNoun.raiseIntake();
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
