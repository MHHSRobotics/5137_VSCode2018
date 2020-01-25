package org.usfirst.frc.team5137.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ButtonReleased extends Command {
	
	RepeatsInTeleop command;
	
	public ButtonReleased(RepeatsInTeleop command) {
		this.command = command;
	}
	
	public void execute() {
		command.reset();
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
