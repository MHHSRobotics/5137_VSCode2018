package org.usfirst.frc.team5137.commands;

import org.usfirst.frc.team5137.robot.Robot;
import org.usfirst.frc.team5137.robot.RobotMap;
import org.usfirst.frc.team5137.subsystems.DriveBase;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * Displays important values on the SmartDashboard.
 */
public class DisplayValues extends Command {
	
	protected void execute() {
		SmartDashboard.putNumber("Timer", (int)(Robot.timer.get() * 10 / 10)); // displays the time in seconds
		SmartDashboard.putNumber("Left encoder", RobotMap.leftEncoder.getDistance());
		SmartDashboard.putNumber("Right encoder", RobotMap.rightEncoder.getDistance());
		SmartDashboard.putBoolean("Compressor enabled", RobotMap.compressor.enabled());
		SmartDashboard.putNumber("Drive speed", DriveBase.driveSpeed);
	}
	
	protected boolean isFinished() {
		return false;
	}

}
