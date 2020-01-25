package org.usfirst.frc.team5137.commands;


import org.usfirst.frc.team5137.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class VisionDrive extends Command {
    	
    public VisionDrive () {
    	requires(Robot.driveBase);
    }

    protected void execute() {
    	Robot.driveBase.visionDrive();
    }
    
    protected void interrupted() {
        end();
    }
    
    protected void end() {
        Robot.driveBase.stop();
    }
   
    protected boolean isFinished() {
         return false;
    }
    
}