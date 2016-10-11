// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.team2667.stronghold.subsystems;

import org.team2667.stronghold.RobotMap;
import org.team2667.stronghold.commands.Drive;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class DriveTrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final CANTalon leftFrontCAN = RobotMap.driveTrainLeftFrontCAN;
    private final CANTalon leftRearCAN = RobotMap.driveTrainLeftRearCAN;
    private final CANTalon rightFrontCAN = RobotMap.driveTrainRightFrontCAN;
    private final CANTalon rightRearCAN = RobotMap.driveTrainRightRearCAN;
    private final RobotDrive robotDriveCAN = RobotMap.driveTrainRobotDriveCAN;
    private final SpeedController leftFrontPWM = RobotMap.driveTrainLeftFrontPWM;
    private final SpeedController leftRearPWM = RobotMap.driveTrainLeftRearPWM;
    private final SpeedController rightFrontPWM = RobotMap.driveTrainRightFrontPWM;
    private final SpeedController rightRearPWM = RobotMap.driveTrainRightRearPWM;
    private final RobotDrive robotDrivePWM = RobotMap.driveTrainRobotDrivePWM;
    private final AnalogGyro gyro = RobotMap.driveTrainGyro;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    
    // Compatibility with different motor controllers
    public boolean usingPWM() {
    	return false;
    }
    
    public SpeedController getLeftFront() {
    	if (usingPWM()) {
    		return this.leftFrontPWM;
    	}
    	return this.leftFrontCAN;
    }
    
    public SpeedController getLeftRear() {
    	if (usingPWM()) {
    		return this.leftRearPWM;
    	}
    	return this.leftRearCAN;
    }
    
    public SpeedController getRightFront() {
    	if (usingPWM()) {
    		return this.rightFrontPWM;
    	}
    	return this.rightFrontCAN;
    }
    
    public SpeedController getRightRear() {
    	if (usingPWM()) {
    		return this.rightRearPWM;
    	}
    	return this.rightRearCAN;
    }
    
    public RobotDrive getRobotDrive() {
    	if (usingPWM()) {
    		return this.robotDrivePWM;
    	}
    	return this.robotDriveCAN;
    }
    
    public double getAngle() {
    	return this.gyro.getAngle();
    }
    
    public void tankDrive(GenericHID controller) {
    	getRobotDrive().tankDrive(controller.getX(), controller.getY());
    }
    
    public void arcadeDrive(GenericHID controller) {
    	getRobotDrive().arcadeDrive(-controller.getY(), -controller.getX(), true);
    }
    
    public void mecanumDrive(GenericHID controller) {
    	this.mecanumDrive(controller, 0);
    }
    
    public void mecanumDrive(GenericHID controller, double gyroAngle) {
    	getRobotDrive().mecanumDrive_Cartesian(controller.getX(), controller.getY(), controller.getZ(), gyroAngle);
    }
    
    public void setMaxOutput(double maxOutput) {
    	getRobotDrive().setMaxOutput(maxOutput);
    }
    
    public void setSensitivity(double sensitivity) {
    	getRobotDrive().setSensitivity(sensitivity);
    }
    
    public void stop() {
    	this.getRobotDrive().stopMotor();
    }
    
    public double getDistanceToObstacle() {
    	return RobotMap.rangeFinder.getVoltage() * 10;
    }
    
}

