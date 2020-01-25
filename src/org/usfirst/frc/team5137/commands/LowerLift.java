package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;
import org.usfirst.frc.team5137.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/*
 * Lowers the lift subsystem. Works in auto and teleop. 
 * If auto, tell it how long. Stops if it hits lower
 * limit switch.
 */
public class LowerLift extends Command {

	Timer timer;
	
	double howLong;
	boolean isTimed;
	boolean timerRunning;
	boolean isFinished;
	
	public LowerLift() {
		requires(Robot.lift);
		isTimed = false;
		isFinished = false;
	} 
	
	public LowerLift(double howLong) {
		requires(Robot.lift);
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
			if (timer.get() < howLong && RobotMap.lowerLimitSwitch.get()) { // true = not pressed, false = pressed
				Robot.lift.lowerLift();
			} else {
				isFinished = true;
			}
		} else {
			if (RobotMap.lowerLimitSwitch.get()) Robot.lift.lowerLift();
			else Robot.lift.stop();
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
	
}
