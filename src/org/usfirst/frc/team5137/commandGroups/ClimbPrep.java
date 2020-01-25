package org.usfirst.frc.team5137.commandGroups;

import org.usfirst.frc.team5137.commands.LowerIntake;
import org.usfirst.frc.team5137.commands.LowerLift;
import org.usfirst.frc.team5137.commands.RaiseLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * Prepares the robot for climbing by lowering the intake system
 * and raising the lift to max height. To be activated by driver
 * with the press of a button when the 30 second alarm initiates.
 */
public class ClimbPrep extends CommandGroup {

	public ClimbPrep() {
		addSequential(new RaiseLift(), 1.5);
		addSequential(new LowerIntake(), 3);
		addSequential(new LowerLift(), 1.5);
	}
	
}
