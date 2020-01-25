package org.usfirst.frc.team5137.subsystems;

import org.usfirst.frc.team5137.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lift extends Subsystem {

	Spark liftMotor = RobotMap.liftMotor;
	
	public void raiseLift() {
		liftMotor.set(.95);
	}
	
	public void lowerLift() {
		liftMotor.set(-.7);
	}
	
	public void stop() {
		liftMotor.set(0);
	}
	
	protected void initDefaultCommand() {

	}
	
}
