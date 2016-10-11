// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.team2667.stronghold;

import org.team2667.stronghold.commands.Autonomous360NoScope;
import org.team2667.stronghold.commands.AutonomousDoNothing;
import org.team2667.stronghold.commands.AutonomousLowBar;
import org.team2667.stronghold.commands.AutonomousMoat;
import org.team2667.stronghold.commands.AutonomousRoughTerrain;
import org.team2667.stronghold.commands.AutonomousRoughTerrainReverse;
import org.team2667.stronghold.subsystems.BallManipulator;
import org.team2667.stronghold.subsystems.DriveTrain;
import org.team2667.stronghold.subsystems.LiftArm;
import org.team2667.stronghold.subsystems.Pnumatics;
import org.team2667.stronghold.subsystems.Vision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	SendableChooser chooser;
	
	public static OI oi;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveTrain driveTrain;
    public static Vision vision;
    public static BallManipulator ballManipulator;
    public static Pnumatics pnumatics;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	private static CameraServer cameraServer;
	private static USBCamera defaultCamera;
	
	private static PowerDistributionPanel powerDistributionPanel;
	
	public static LiftArm liftArm;
	
	public Robot() {
		try{
			defaultCamera = new USBCamera("cam1");
			cameraServer = CameraServer.getInstance();
			cameraServer.setQuality(25);
			cameraServer.startAutomaticCapture(defaultCamera);
			System.out.println("Camera connected.");
		} catch(Exception e){
			System.out.println("Your camera is not plugged in");
		}
		
		powerDistributionPanel = new PowerDistributionPanel();
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println("Initializing robot.");
		RobotMap.init();
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveTrain = new DriveTrain();
        vision = new Vision();
        ballManipulator = new BallManipulator();
        pnumatics = new Pnumatics();
    	liftArm = new LiftArm();
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		// OI must be constructed after subsystems. If the OI creates Commands
		//(which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// instantiate the command used for the autonomous period
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        autonomousCommand = new AutonomousLowBar();

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
		
		chooser = new SendableChooser();
		chooser.addDefault("Low Bar", new AutonomousLowBar());
		chooser.addObject("Rough Terrain 80% @ 4s", new AutonomousRoughTerrain());
		
		chooser.addObject("Rough Terrain Reverse 80% @ 4sec", new AutonomousRoughTerrainReverse());
		chooser.addObject("Moat 80% @ 3.5s", new AutonomousMoat());
		
		chooser.addObject("360 No-Scope (For madmen)", new Autonomous360NoScope());
		chooser.addObject("Do Nothing", new AutonomousDoNothing());
		SmartDashboard.putData("Auto Mode", chooser);
		
	
	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	public void disabledInit(){
		System.out.println("Disabled");
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		ballManipulator.retractSolenoids();
	}

	public void autonomousInit() {
		// Set the autonomous command to the selected command
		autonomousCommand = (Command) chooser.getSelected();
		
		System.out.println("Initializing Autonomous: " + autonomousCommand.getName());
		
		// schedule the autonomous command (example)
		if (autonomousCommand != null) autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		// SmartDashboard
		SmartDashboard.putNumber("Gyro Angle", driveTrain.getAngle());
		SmartDashboard.putNumber("Range Finder", Robot.driveTrain.getDistanceToObstacle());
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
	
	public PowerDistributionPanel getPowerDistributionPanel() {
		return powerDistributionPanel;
	}
}
