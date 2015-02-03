package org.usfirst.frc.team293.robot;

import SpikeLibrary.SpikeButton;
import SpikeLibrary.SpikeLEDButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.DigitalOutput;
import subsystems.Arm;
import subsystems.DriveTrain;
import subsystems.Elevator;
import subsystems.PDP;

public class OI {
	private static final Joystick leftJoystick = new Joystick(Ports.leftJoystick);
	private static final Joystick rightJoystick = new Joystick(Ports.rightJoystick);
	private static final Joystick launchpad = new Joystick(Ports.launchpad);
	
	private static final SpikeLEDButton elevator0B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator1B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator2B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator3B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator4B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator5B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator6B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator7B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	
	private static final SpikeButton arm0B = new SpikeButton(launchpad, Ports.arm0B);
	private static final SpikeButton arm1B = new SpikeButton(launchpad, Ports.arm1B);
	
	
	
	private static int periodCounter = 0;
	private static boolean flashState = true;
	
	public static void controlDriveTrain() {
		DriveTrain.tankDrive(leftJoystick.getY(), rightJoystick.getY());
	}
	
	public static void controlArm() {
		if (launchpad.getRawAxis(Ports.armAxis) != 0) {
			Arm.setMode(true);
			Arm.move(launchpad.getRawAxis(Ports.armAxis));
		} /* else {
			if (arm0B.isBumped()) {
				Arm.setPosition(0);
				Arm.setMode(false);
			}
			
			if (arm1B.isBumped()) {
				Arm.setPosition(1);
				Arm.setMode(false);
			}
			
			if (!Arm.getMode()) {
				Arm.goToPosition();
			}

		} */
	}
	
	public static void controlElevator() {
		if (launchpad.getRawAxis(Ports.elevatorAxis) != 0) {
			Elevator.setMode(true);
			Elevator.move(launchpad.getRawAxis(Ports.elevatorAxis));
		} /* else {
			if (elevator0B.isBumped()) {
				Elevator.setPosition(0);
				Elevator.setMode(false);
			}
			
			if (elevator1B.isBumped()) {
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
