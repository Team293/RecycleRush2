package org.usfirst.frc.team293.robot;

import edu.wpi.first.wpilibj.Joystick;
import subsystems.Arm;
import subsystems.DriveTrain;
import subsystems.PDP;

public class OI {
	private static final Joystick leftJoystick = new Joystick(Ports.leftJoystick);
	private static final Joystick rightJoystick = new Joystick(Ports.rightJoystick);
	private static final Joystick gamepad = new Joystick(Ports.gamepad);
	
	public static void controlDriveTrain() {
		DriveTrain.tankDrive(leftJoystick.getY(), rightJoystick.getY());;
	}
	
	public static void controlArm() {
		if (gamepad.getRawAxis(Ports.rightYAxis) != 0) {
			Arm.setMode(true);
			Arm.move(gamepad.getRawAxis(Ports.rightYAxis));
		}
		
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
	}
	
	public static void controlDoor() {
		
	}
	
	public static void controlElevator() {
		
	}
	
	public static void controlPDP() {
		PDP.monitor();
	}

}
