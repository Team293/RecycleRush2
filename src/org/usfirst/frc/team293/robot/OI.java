package org.usfirst.frc.team293.robot;

import SpikeLibrary.SpikeButton;
import edu.wpi.first.wpilibj.Joystick;
import subsystems.Arm;
import subsystems.DriveTrain;
import subsystems.Elevator;
import subsystems.PDP;

public class OI {
	private static final Joystick leftJoystick = new Joystick(Ports.leftJoystick);
	private static final Joystick rightJoystick = new Joystick(Ports.rightJoystick);
	private static final Joystick gamepad = new Joystick(Ports.gamepad);
	
	private static final SpikeButton elevatorUpB = new SpikeButton(gamepad, Ports.rightBumper);
	private static final SpikeButton elevatorDownB = new SpikeButton(gamepad, Ports.leftBumper);
	
	public static void controlDriveTrain() {
		DriveTrain.tankDrive(leftJoystick.getY(), rightJoystick.getY());;
	}
	
	public static void controlArm() {
		if (gamepad.getRawAxis(Ports.rightYAxis) != 0) {
			Arm.setMode(true);
			Arm.move(gamepad.getRawAxis(Ports.rightYAxis));
		} /* else {
			if (position0Button.isBumped()) {
				Arm.setPosition(0);
				Arm.setMode(false);
			}
			
			if (position1Button.isBumped()) {
				Arm.setPosition(1);
				Arm.setMode(false);
			}
			
			if (!Arm.getMode()) {
				Arm.goToPosition();
			}

		} */
	}
	private static double manualElevatorSpeed() {
		if (elevatorUpB.isHeld()) {
			return 1;
		} else if (elevatorDownB.isHeld()) {
			return -1;
		}
		return 0;
	}
	
	public static void controlElevator() {
		if (manualElevatorSpeed() != 0) {
			Elevator.setMode(true);
			Elevator.move(manualElevatorSpeed());
		} /* else {
			if (position0Button.isBumped()) {
				Elevator.setPosition(0);
				Elevator.setMode(false);
			}
			
			if (position1Button.isBumped()) {
				Elevator.setPosition(1);
				Elevator.setMode(false);
			}
			
			if (!Elevator.getMode()) {
				Elevator.goToPosition();
			}
		} */
	}
	
	public static void controlPDP() {
		PDP.monitor();
	}

}
