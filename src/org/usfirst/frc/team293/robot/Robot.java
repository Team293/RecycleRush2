
package org.usfirst.frc.team293.robot;

import autonomous.Auto;
import autonomous.BinSet;
import autonomous.BinToteSet;
import autonomous.BinToteStack;
import autonomous.SensorDriveStraight;
import autonomous.ToteStack;
import subsystems.Arm;
import subsystems.DriveTrain;
import subsystems.Elevator;
import subsystems.PDP;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */

	//SendableChooser autonomousChooser = new SendableChooser();
	String[] autonomiNames;
	Auto[] autonomi;
	Auto selectedAuto;

	public void robotInit() {
		DriveTrain.gyroInit(); //for straightdrive
		SensorDriveStraight.init();
		/*autonomousChooser.addObject("bin set", new BinSet());
		autonomousChooser.addObject("bin & tote set", new BinToteSet());
		autonomousChooser.addObject("bin set & tote stack", new BinToteStack());
		autonomousChooser.addObject("robot set", new RobotSet());
		autonomousChooser.addObject("tote set", new ToteSet());
		autonomousChooser.addObject("tote stack", new ToteStack());

		SmartDashboard.putData("Which Autonomous?", autonomousChooser);*/
	}

	public void autonomousInit() {
		//selectedAuto = (Auto) autonomousChooser.getSelected();
		
		//selectedAuto.init();
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		
		//	selectedAuto.run();
		//DriveTrain.enable();
		//DriveTrain.turnleft();
		SensorDriveStraight.blah();	
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopInit() {
		Elevator.reset();
	}
	
	public void teleopPeriodic() {
		OI.controlDriveTrain();
		Arm.viewPosition();
		OI.controlElevator();
		OI.controlArm();
		OI.controlPDP();
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {

	}

}
