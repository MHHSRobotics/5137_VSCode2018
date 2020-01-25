package org.usfirst.frc.team5137.commandGroups;

import org.usfirst.frc.team5137.commands.DisplayValues;
import org.usfirst.frc.team5137.commands.DriveForward;
import org.usfirst.frc.team5137.commands.LowerIntake;
import org.usfirst.frc.team5137.commands.Outtake;
import org.usfirst.frc.team5137.commands.Pivot;
import org.usfirst.frc.team5137.commands.RaiseLift;
import org.usfirst.frc.team5137.commands.Wait;
import org.usfirst.frc.team5137.commands.EncoderDriveForward;
import org.usfirst.frc.team5137.commands.EncoderPivot90;

import edu.wpi.first.wpilibj.command.CommandGroup;

/*
 * Autonomous routine in which the robot starts from the center of the field,
 * drives forward 7 feet, drives sideways (direction determined by gameData) 
 * 5 feet, then drives forward the final 4 feet and outtakes the power cube
 * onto the switch.
 */
public class CenterAutoSwitch extends CommandGroup implements RequiresGameData {

	private DisplayValues displayValues;
	private LowerIntake lowerIntake;
	private RaiseLift raiseLift;
	private EncoderDriveForward driveForward1;
	private EncoderPivot90 pivot1;
	//private Pivot pivot1;
	private EncoderDriveForward driveForward2;
	private EncoderPivot90 pivot2;
	//private Pivot pivot2;
	private DriveForward driveForward3;
	private Outtake outtake;

	public CenterAutoSwitch() {
		displayValues = new DisplayValues();
		lowerIntake = new LowerIntake();
		raiseLift = new RaiseLift();
		driveForward1 = new EncoderDriveForward(2 * 12, .65); // 2 feet
		pivot1 = new EncoderPivot90(.6); // about 90 degrees
		//pivot1 = new Pivot(.65);
		driveForward2 = new EncoderDriveForward(3.25 * 12, .65); // 3.5 feet (lateral)
		pivot2 = new EncoderPivot90(.6); // pivot back (direction set below)
		//pivot2 = new Pivot(.65);
		driveForward3 = new DriveForward(.65); // rest of the forward distance
		outtake = new Outtake();
		
		addParallel(displayValues);
		addSequential(lowerIntake, 1);
		addSequential(raiseLift, 1.5);
		addSequential(driveForward1);
		addSequential(new Wait(), .5);
		addSequential(pivot1);
		addSequential(new Wait(), .5);
		addSequential(driveForward2);
		addSequential(new Wait(), .5);
		addSequential(pivot2);
		addSequential(new Wait(), .5);
		addSequential(driveForward3, 2.5);
		addSequential(outtake, 1);
	}
	
	// sets the direction of the pivots
	public void setGameData(String gameData) {
		if (gameData.length() > 0) {
		    if (gameData.charAt(0) == 'L') {
		    	pivot1.setDirection(true);
		    	pivot2.setDirection(false);
		    } else {
		    	pivot1.setDirection(false);
		    	pivot2.setDirection(true);
		    }
		} else {
			pivot1.setDirection(true);
	    	pivot2.setDirection(false);
		}
	}
	
}