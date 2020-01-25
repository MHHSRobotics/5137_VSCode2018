package org.usfirst.frc.team5137.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ButtonPressed extends Command {

	RepeatsInTeleop command;
	
	public ButtonPressed(RepeatsInTeleop command) {
		this.command = command;
	}
	
	public void execute() {
		command.execute();
	}
	
	protected void interrupted() {
		end();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		
	}
	
}
