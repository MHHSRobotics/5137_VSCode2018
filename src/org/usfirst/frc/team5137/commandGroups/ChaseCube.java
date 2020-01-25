package org.usfirst.frc.team5137.commandGroups;

import org.usfirst.frc.team5137.commands.IntakeVerb;
import org.usfirst.frc.team5137.commands.VisionDrive;
import org.usfirst.frc.team5137.commands.CloseIntake;
import org.usfirst.frc.team5137.commands.DriveForward;

import edu.wpi.first.wpilibj.command.CommandGroup;

// doesn't work. VisionDrive is probably the problem
public class ChaseCube extends CommandGroup{

	public ChaseCube() {
		addSequential(new VisionDrive(), .2);
		addSequential(new DriveForward(.65), .2); // might be too long
		addSequential(new CloseIntake(), .2);
		addSequential(new IntakeVerb(), 1);
	}
	
}
