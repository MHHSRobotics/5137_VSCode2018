package org.usfirst.frc.team5137.commandGroups;

import org.usfirst.frc.team5137.commands.EncoderDriveForward;
import org.usfirst.frc.team5137.commands.LowerIntake;
import org.usfirst.frc.team5137.commands.Outtake;
import org.usfirst.frc.team5137.commands.Pivot;
import org.usfirst.frc.team5137.commands.RaiseLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RightAutoScale extends CommandGroup implements RequiresGameData {

	private EncoderDriveForward driveForward;
	private LowerIntake lowerIntake;
	private RaiseLift raiseLift;
	private Pivot pivot;
	private Outtake outtake;
	
	public RightAutoScale() {
		driveForward = new EncoderDriveForward(12 * 18.6, .65);
		lowerIntake = new LowerIntake();
		raiseLift = new RaiseLift();
		pivot = new Pivot(-.65);
		outtake = new Outtake();
		
		addSequential(driveForward);
		addSequential(lowerIntake, 1);
		addSequential(raiseLift, 3);
		addSequential(pivot, 1.8);
		addSequential(outtake, 1);
	}
	
	public void setGameData(String gameData) {
		if (gameData.length() > 0) {
		    if (gameData.charAt(0) == 'R') {
		    	raiseLift.setWillRun(true);
		    	outtake.setWillRun(true);
		    } else {
		    	raiseLift.setWillRun(false);
		    	outtake.setWillRun(false);
		    }
		} else {
			raiseLift.setWillRun(false);
			outtake.setWillRun(false);
		}
	}
}
