package org.usfirst.frc.team5137.subsystems;

import org.usfirst.frc.team5137.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeNoun extends Subsystem {

	Spark rotateIntakeMotor = RobotMap.rotateIntakeMotor;
	Spark intakeMotor = RobotMap.intakeMotor;
	DoubleSolenoid pneumaticThing = RobotMap.pneumaticThing;
	
	public void raiseIntake() {
		rotateIntakeMotor.set(.65);
	}
	
	public void lowerIntake() {
		rotateIntakeMotor.set(-.5);
	}
	
	public void openIntake() {
		pneumaticThing.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void closeIntake() {
		pneumaticThing.set(DoubleSolenoid.Value.kForward);
	}
	
	public void intake() {
		intakeMotor.set(-.6);
	}
	
	public void outtake() {
		intakeMotor.set(.7);
	}
	
	public void stop() {
		rotateIntakeMotor.set(0);
		pneumaticThing.set(DoubleSolenoid.Value.kOff);
		intakeMotor.set(0);
	}
	
	protected void initDefaultCommand() {
		
	}

}
