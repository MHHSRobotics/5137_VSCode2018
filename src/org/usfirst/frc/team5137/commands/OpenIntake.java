package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class OpenIntake extends Command {

	boolean isFinished;
	
	public OpenIntake() {
		requires(Robot.intakeNoun);
		isFinished = false;
	} 
	
	protected void execute() {
		Robot.intakeNoun.openIntake();
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
