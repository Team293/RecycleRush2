
package org.usfirst.frc.team293.robot;

import autonomous.Auto;
<<<<<<< HEAD
import autonomous.Bin;
import autonomous.BinTote;
import autonomous.BinToteTurn;
import autonomous.BinTurn;
import autonomous.RobotSet;
=======
import autonomous.BinSet;
import autonomous.BinToteSet;
import autonomous.BinToteStack;
import autonomous.SensorDriveStraight;
import autonomous.ToteStack;
>>>>>>> origin/ImprovedAutonomous
import subsystems.Arm;
import subsystems.DriveTrain;
import subsystems.Elevator;
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
<<<<<<< HEAD
		SmartDashboard.putNumber("liftT", 0);
		SmartDashboard.putNumber("captureT", 0);
		SmartDashboard.putNumber("turnT", 0);
		SmartDashboard.putNumber("driveT", 0);
		SmartDashboard.putNumber("turnT2", 0);
		
		autonomousChooser.addObject("bin", new Bin());
		autonomousChooser.addObject("bin & turn", new BinTurn());
		autonomousChooser.addObject("bin & tote", new BinTote());
		autonomousChooser.addObject("bin, tote & turn", new BinToteTurn());
=======
		DriveTrain.gyroInit(); //for straightdrive
		SensorDriveStraight.init();
		/*autonomousChooser.addObject("bin set", new BinSet());
		autonomousChooser.addObject("bin & tote set", new BinToteSet());
		autonomousChooser.addObject("bin set & tote stack", new BinToteStack());
>>>>>>> origin/ImprovedAutonomous
		autonomousChooser.addObject("robot set", new RobotSet());

		SmartDashboard.putData("Which Autonomous?", autonomousChooser);
	}

	public void autonomousInit() {
<<<<<<< HEAD
		selectedAuto = (Auto) autonomousChooser.getSelected();
		Elevator.reset();
=======
		//selectedAuto = (Auto) autonomousChooser.getSelected();
		
		//selectedAuto.init();
>>>>>>> origin/ImprovedAutonomous
		
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
