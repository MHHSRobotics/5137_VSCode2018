package org.usfirst.frc.team5137.commandGroups;

import org.usfirst.frc.team5137.commands.CloseIntake;
import org.usfirst.frc.team5137.commands.IntakeVerb;

import edu.wpi.first.wpilibj.command.CommandGroup;

// it works but he don't like it
public class GrabCube extends CommandGroup {
	
	public GrabCube() {
		addSequential(new IntakeVerb(), 1);
		addSequential(new CloseIntake(), .2);
		addSequential(new IntakeVerb(), .5);
	}
	
}
