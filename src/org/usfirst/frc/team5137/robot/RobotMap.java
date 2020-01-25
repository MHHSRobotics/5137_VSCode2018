package org.usfirst.frc.team5137.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/* The robot map is used to define and categorize all things on the robot...
 * This includes: motors, gyros and specialty things such as the
 * DifferentialDrive- This allows the drive motors (not the slidedrive) to 
 * function together and do things such as arcade drive and tank drive 
 * more readily as seen in the DriveBase code.
 */
public class RobotMap {

	public static Spark leftDriveMotor;
	public static Spark rightDriveMotor;
	public static Spark liftMotor;
	public static Spark rotateIntakeMotor;
	public static Spark intakeMotor;
	public static DifferentialDrive hotWheels;
	public static Encoder leftEncoder;
	public static Encoder rightEncoder;
	public static DigitalInput upperLimitSwitch;
	public static DigitalInput lowerLimitSwitch;
	public static Compressor compressor;
	public static DoubleSolenoid pneumaticThing;
	public static ADXRS450_Gyro gyro;
	
	public static void init() {
		leftDriveMotor = new Spark(0);
		leftDriveMotor.setInverted(true);
		
		rightDriveMotor = new Spark(2); // 2018 bot is 2, 2017 bot is 1
		rightDriveMotor.setInverted(true);
		
		hotWheels = new DifferentialDrive(leftDriveMotor, rightDriveMotor);
		
		liftMotor = new Spark(3);
		liftMotor.setInverted(true);
		
		rotateIntakeMotor = new Spark(4); 
		intakeMotor = new Spark(5); 
		intakeMotor.setInverted(true);
		
		leftEncoder = new Encoder(0, 1, true, Encoder.EncodingType.k4X);
		leftEncoder.setDistancePerPulse(.1173); 
		leftEncoder.setMinRate(10);
		
		rightEncoder = new Encoder(4, 5, false, Encoder.EncodingType.k4X);
		rightEncoder.setDistancePerPulse(.1173);
		rightEncoder.setMinRate(10);
		
		upperLimitSwitch = new DigitalInput(9);
		lowerLimitSwitch = new DigitalInput(8);
		
		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);
		
		pneumaticThing = new DoubleSolenoid(0, 1);
		
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
	}
	
}
