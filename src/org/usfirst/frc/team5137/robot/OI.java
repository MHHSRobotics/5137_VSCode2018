package org.usfirst.frc.team5137.robot;

import org.usfirst.frc.team5137.commands.CloseIntake;
import org.usfirst.frc.team5137.commands.EncoderPivot90;
import org.usfirst.frc.team5137.commands.GyroDriveStraight;
import org.usfirst.frc.team5137.commands.GyroPivot;
import org.usfirst.frc.team5137.commands.IntakeVerb;
import org.usfirst.frc.team5137.commands.LowerIntake;
import org.usfirst.frc.team5137.commands.LowerLift;
import org.usfirst.frc.team5137.commands.OpenIntake;
import org.usfirst.frc.team5137.commands.Outtake;
import org.usfirst.frc.team5137.commands.RaiseIntake;
import org.usfirst.frc.team5137.commands.RaiseLift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/*
 * Everything to do with the controller and its buttons.
 */
public class OI {
	
	// I don't think these have to be public but I know it works.
	public Joystick jackBlack;
	public Joystick jacob;
	
	public JoystickButton raiseLiftButton;
	public JoystickButton lowerLiftButton;
	public JoystickButton raiseIntakeButton;
	public JoystickButton lowerIntakeButton;
	public JoystickButton intakeButton;
	public JoystickButton outtakeButton;
	public JoystickButton openIntakeButton;
	public JoystickButton closeIntakeButton;
	public JoystickButton aimBotButton;
	
	public JoystickButton testButton1;
	public JoystickButton testButton2;
	public JoystickButton testButton3;
	public JoystickButton testButton4;
	
	public OI() {
		
		jackBlack = new Joystick(0);
		
		raiseLiftButton = new JoystickButton(jackBlack, 3); // X
		raiseLiftButton.whileHeld(new RaiseLift());
		
		lowerLiftButton = new JoystickButton(jackBlack, 4); // Y
		lowerLiftButton.whileHeld(new LowerLift());
		
		/*
		// testtesttest
		testButton1 = new JoystickButton(jackBlack, 1); // A
		testButton1.whenPressed(new EncoderPivot90(.75, true)); // should pivot 90 degrees clockwise
		
		testButton2 = new JoystickButton(jackBlack, 2); // B
		testButton2.whenPressed(new GyroPivot(90, .75)); // should pivot 90 degrees clockwise
		
		testButton3 = new JoystickButton(jackBlack, 5); // LB
		testButton3.whileHeld(new GyroDriveStraight(.65)); // should drive straight toward heading 0
		
		testButton4 = new JoystickButton(jackBlack, 6); // RB
		testButton4.whenPressed(new EncoderPivot(2 * 12, .75, true)); // should pivot 90 degrees CCW
		*/
		
		jacob = new Joystick(1);
		
		openIntakeButton = new JoystickButton(jacob, 1); // A
		openIntakeButton.whenPressed(new OpenIntake());
		
		closeIntakeButton = new JoystickButton(jacob, 2); // B
		closeIntakeButton.whenPressed(new CloseIntake());
		
		intakeButton = new JoystickButton(jacob, 3); // X
		intakeButton.whileHeld(new IntakeVerb());
		
		outtakeButton = new JoystickButton(jacob, 4); // Y
		outtakeButton.whileHeld(new Outtake());
		
		raiseIntakeButton = new JoystickButton(jacob, 5); // LB
		raiseIntakeButton.whileHeld(new RaiseIntake());
		
		lowerIntakeButton = new JoystickButton(jacob, 6); // RB
		lowerIntakeButton.whileHeld(new LowerIntake()); 
		
	}
	
}
