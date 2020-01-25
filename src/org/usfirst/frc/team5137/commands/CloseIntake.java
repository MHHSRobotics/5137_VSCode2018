package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class CloseIntake extends Command {

	boolean isFinished;
	
	public CloseIntake() {
		requires(Robot.intakeNoun);
		isFinished = false;
	} 
	
	protected void execute() {
		Robot.intakeNoun.closeIntake();
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