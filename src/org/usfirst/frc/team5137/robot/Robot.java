/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5137.robot;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team5137.commandGroups.CenterAutoSwitch;
import org.usfirst.frc.team5137.commandGroups.DelayTimerLeftAutoSwitch;
import org.usfirst.frc.team5137.commandGroups.DelayTimerRightAutoSwitch;
import org.usfirst.frc.team5137.commandGroups.LeftAutoScale;
import org.usfirst.frc.team5137.commandGroups.RequiresGameData;
import org.usfirst.frc.team5137.commandGroups.RightAutoScale;
import org.usfirst.frc.team5137.commandGroups.TimerLeftAutoSwitch;
import org.usfirst.frc.team5137.commandGroups.TimerRightAutoSwitch;
import org.usfirst.frc.team5137.commands.DisplayValues;
import org.usfirst.frc.team5137.commands.TimerDriveForward;
import org.usfirst.frc.team5137.grip.GripPipelineVOne;
import org.usfirst.frc.team5137.subsystems.DriveBase;
import org.usfirst.frc.team5137.subsystems.IntakeNoun;
import org.usfirst.frc.team5137.subsystems.Lift;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.VisionThread;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */

/*	The Robot class acts as a kind of hub for all other classes, including subsystems and commands. 
 * For example, by stating that the DriveBase is static and public, 
 * it allows the whole robot program to find it.  With out doing this, no class could find another.
 */

public class Robot extends TimedRobot {
	
	public static DriveBase driveBase; 
	public static Lift lift;
	public static IntakeNoun intakeNoun;

	public static OI oi;	
	
	public static UsbCamera calebCamera;
	public static UsbCamera rohanCamera;
	public static Timer timer; 
	
	public static Object imgLock;
	public static VisionThread visionThread;
	
	public static double centerX = 0.0;
	
	public static String gameData;
	
	Command autonomousCommand; 
	SendableChooser<Command> autoChooser;
	
	/*	
	 * robotInit() is the first thing the robot does on boot up
	 * it is used to declare what subsystems and the OI are and to calibrate any gyros 
	 * and start timers
	 */
	public void robotInit() {
		RobotMap.init();
	   	 
	   	driveBase = new DriveBase();
	   	lift = new Lift();
	   	intakeNoun = new IntakeNoun();
	   	
		oi = new OI(); // gotta go after all the subsystems!
		
		calebCamera = CameraServer.getInstance().startAutomaticCapture();
		calebCamera.setResolution(320, 240);
		calebCamera.setFPS(30); 
		
		
		timer = new Timer();
		
		visionThread =  new VisionThread(calebCamera, new GripPipelineVOne(), pipeline -> {
			if (!pipeline.filterContoursOutput().isEmpty()) {
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
                synchronized (imgLock) {
                    centerX = r.x + (r.width / 2);
                }
            }
        });
        visionThread.start();
		
		// adds autonomous options and displays them on the SmartDashboard
		autoChooser = new SendableChooser<Command>();
		autoChooser.addDefault("Switch from center", new CenterAutoSwitch());
		autoChooser.addObject("Switch from right", new TimerRightAutoSwitch());
		autoChooser.addObject("Cross the auto line", new TimerDriveForward(4, .65));
		autoChooser.addObject("Switch from left", new TimerLeftAutoSwitch());
		autoChooser.addObject("Switch from left, delay", new DelayTimerLeftAutoSwitch());
		autoChooser.addObject("Switch from right, delay", new DelayTimerRightAutoSwitch());
		autoChooser.addObject("Scale from left", new LeftAutoScale());
		autoChooser.addObject("Scale from right", new RightAutoScale());
		SmartDashboard.putData("Autonomous mode chooser", autoChooser);	
	}
	
	/*
	 * The below code instructs the robot what to do when Autonomous mode is first pressed
	 */
	public void autonomousInit() {
		int retries = 100;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		while (gameData.length() < 2 && retries > 0) {
		    retries--;
		    try {
		        Thread.sleep(5);
		    } catch (InterruptedException ie) {
		        // Just ignore the interrupted exception
		    }
		    gameData = DriverStation.getInstance().getGameSpecificMessage();
		}
		SmartDashboard.putString("gameData", gameData);
		autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand instanceof RequiresGameData)
		{
			((RequiresGameData)autonomousCommand).setGameData(gameData);
		}
		autonomousCommand.start();
		timer.reset();
		timer.start();		
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	// You have to tell the robot to stop doing autonomous stuff
	public void teleopInit() {
		if (autonomousCommand != null) autonomousCommand.cancel();
		timer.reset();
		timer.start();
		DisplayValues displayValues = new DisplayValues();
		displayValues.start();
	}
	
	/* teleopPeriodic runs any default commands defined in any subsystems, 
	 * typically, only the driveBase has one set
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();		
	}

}
