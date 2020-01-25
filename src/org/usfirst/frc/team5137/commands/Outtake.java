package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/*
 * Spits out the power cube. Works in autonomous and teleop.
 * If auto, tell it how long to do it.
 */
public class Outtake extends Command {

	Timer timer;
	
	double howLong;
	boolean isTimed;
	boolean timerRunning;
	boolean willRun; // For EncoderLeftAutoSwitch and EncoderRightAutoSwitch
	boolean isFinished;
	
	// Constructor for teleop
	public Outtake() {
		requires(Robot.intakeNoun);
		isTimed = false;
		willRun = true;
		isFinished = false;
	} 
	
	// Constructor for autonomous
	public Outtake(double howLong) {
		requires(Robot.intakeNoun);
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
				if (timer.get() < howLong) {
					Robot.intakeNoun.outtake();
				} else {
					isFinished = true;
				}
			} else {
				Robot.intakeNoun.outtake();
			}
		} else {
			isFinished = true;
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
	
	public void setWillRun(boolean willRun) {
		this.willRun = willRun;
	}
	
}
