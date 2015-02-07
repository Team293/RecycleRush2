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
	//gamepad or custom Launchpad
	public boolean gamepad=true;
	private static final Joystick leftJoystick = new Joystick(Ports.leftJoystick);
	private static final Joystick rightJoystick = new Joystick(Ports.rightJoystick);
	private static final Joystick launchpad = new Joystick(Ports.launchpad);

	/*	private static final SpikeLEDButton elevator0B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator1B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator2B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator3B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator4B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator5B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator6B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	private static final SpikeLEDButton elevator7B = new SpikeLEDButton(launchpad, Ports.elevator0BInput, Ports.elevator0BOutput);
	 */	

	private static final SpikeButton elevator0B = new SpikeButton(launchpad, Ports.elevator0BInput);
	private static final SpikeButton elevator1B = new SpikeButton(launchpad, Ports.elevator1BInput);
	private static final SpikeButton elevator2B = new SpikeButton(launchpad, Ports.elevator2BInput);
	private static final SpikeButton elevator3B = new SpikeButton(launchpad, Ports.elevator3BInput);
	private static final SpikeButton elevator4B = new SpikeButton(launchpad, Ports.elevator4BInput);
	private static final SpikeButton elevator5B = new SpikeButton(launchpad, Ports.elevator5BInput);
	private static final SpikeButton elevator6B = new SpikeButton(launchpad, Ports.elevator6BInput);

	private static final SpikeButton arm0B = new SpikeButton(launchpad, Ports.arm0B);
	private static final SpikeButton arm1B = new SpikeButton(launchpad, Ports.arm1B);



	public static void controlDriveTrain() {
		DriveTrain.tankDrive(leftJoystick.getY(), rightJoystick.getY());
	}

	public static void controlArm() {
		if (launchpad.getRawAxis(Ports.armAxis) != 0) {
			Arm.setMode(true);
			Arm.setPosition(launchpad.getRawAxis(Ports.armAxis));
		} else {
			if (arm0B.isBumped()) {
				Arm.presetPosition(0);
				Arm.setMode(false);
			}

			if (arm1B.isBumped()) {
				Arm.presetPosition(1);
				Arm.setMode(false);
			}

			if (!Arm.getMode()) {
				Arm.goToPosition();
			}

		}
	}

	public static void controlPDP() {
		PDP.monitor();
	}

}
