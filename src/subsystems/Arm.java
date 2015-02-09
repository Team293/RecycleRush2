package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import SpikeLibrary.SpikeMath;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

public class Arm {
	public static final Talon arm = new Talon(Ports.arm);
	private static final AnalogPotentiometer pot = new AnalogPotentiometer(Ports.armPot, 2, -1);
	private static final DigitalInput topLimit = new DigitalInput(Ports.armTopLimit);
	private static final DigitalInput bottomLimit = new DigitalInput(Ports.armBottomLimit);
	private static final PIDController pid = new PIDController(0.1, 0.1, 0.1, pot, arm);
	private static boolean manualMode = true;
	private static double position = 0;
	static double[] positions = new double[] {0,0.25,0.75};
	private static final double tolerance = 0.02;
	
	public static void move(double speed) {
		if (topLimit.get()) {
			speed = SpikeMath.cap(speed, -1, 0);
		} else if (bottomLimit.get()) {
			speed = SpikeMath.cap(speed, 0, 1);
		}
		arm.set(speed);
	}
	
	public static void setMode(boolean newMode) {
		manualMode = newMode;
	}
	
	public static boolean getMode() {
		return manualMode;
	}
	
	public static void presetPosition(int positionInput) {
		position = positions[positionInput];
	}
	
	public static void setPosition(double positionInput) {
		position = positionInput;
	}
	
	public static void goToPosition() {
		pid.setAbsoluteTolerance(tolerance);
		pid.setSetpoint(position);
		pid.enable();
	}



}
