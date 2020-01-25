package org.usfirst.frc.team5137.commandGroups;

import org.usfirst.frc.team5137.commands.DisplayValues;
import org.usfirst.frc.team5137.commands.DriveForward;
import org.usfirst.frc.team5137.commands.LowerIntake;
import org.usfirst.frc.team5137.commands.Outtake;
import org.usfirst.frc.team5137.commands.RaiseLift;
import org.usfirst.frc.team5137.commands.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * Autonomous routine in which the robot starts from the left side of the field,
 * drives forward across the auto line, and outtakes only if the left switch is ours.
 */
public class DelayTimerRightAutoSwitch extends CommandGroup implements RequiresGameData {

	private DisplayValues displayValues;
	private Wait wait;
	private LowerIntake lowerIntake;
	private RaiseLift raiseLift;
	private DriveForward driveForward;
	private Outtake outtake;
	
	public DelayTimerRightAutoSwitch() {
		displayValues = new DisplayValues();
		wait = new Wait();
		lowerIntake = new LowerIntake();
		raiseLift = new RaiseLift();
		driveForward = new DriveForward(.65);
		outtake = new Outtake();
		
		addParallel(displayValues);
		addSequential(wait, 2);
		addSequential(lowerIntake, 1);
		addSequential(raiseLift, 1.2);
     	addSequential(driveForward, 4); 
     	addSequential(outtake, 1);
	}
	
	// tells outtake whether or not to run
	public void setGameData(String gameData) {
		if (gameData.length() > 0) {
		    if (gameData.charAt(0) == 'R') outtake.setWillRun(true);
		    else outtake.setWillRun(false);
		} else outtake.setWillRun(false);
	}
	
}