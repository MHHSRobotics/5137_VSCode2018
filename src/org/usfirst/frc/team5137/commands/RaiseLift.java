package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;
import org.usfirst.frc.team5137.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/*
 * Raises the lift subsystem. Works in auto and teleop.
 * If auto, tell it how long (we never hooked up seat
 * motor encoders). Stops if it hits upper limit switch.
 */
public class RaiseLift extends Command {

	Timer timer;
	
	double howLong;
	boolean isTimed;
	boolean timerRunning;
	boolean willRun;
	boolean isFinished;
	
	public RaiseLift() {
		requires(Robot.lift);
		isTimed = false;
		willRun = true;
		isFinished = false;
	} 
	
	public RaiseLift(double howLong) {
		requires(Robot.lift);
		timer = new Timer();
		this.howLong = howLong;
		isTimed = true;
		timerRunning = false;
		willRun = true;
		isFinished = false;
	}
	
	protected void execute() {
		if (willRun) {
			if (isTimed) {
				if (!timerRunning) {
					timer.reset();
					timer.start();
					timerRunning = true;
				}
				if (timer.get() < howLong && RobotMap.upperLimitSwitch.get()) { // true = not pressed, false = pressed
					Robot.lift.raiseLift();
				} else {
					isFinished = true;
				}
			} else {
				if (RobotMap.upperLimitSwitch.get()) Robot.lift.raiseLift();
				else Robot.lift.stop();
			}
		} else {
			isFinished = true;
		}
	}
	
	protected void interrupted() {
		end();
	}
	
	protected void end() {
		Robot.lift.stop();
	}
	
	protected boolean isFinished() {
		return isFinished;
	}
	
	public void setWillRun(boolean willRun) {
		this.willRun = willRun;
	}
	
}
