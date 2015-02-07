package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
	private static final VictorSP elevator = new VictorSP(Ports.elevator);
	private static final Encoder encoder = new Encoder(Ports.elevatorEncoder1, Ports.elevatorEncoder2);
	private static final DigitalInput topLimit = new DigitalInput(Ports.topLimit);
	private static final DigitalInput bottomLimit = new DigitalInput(Ports.bottomLimit);
	private static boolean manualMode = true;
	private static double position = 0;
	private static double lockSpeed = 0;
	static int[] positions = new int[] {234,2355,2533};
	private static final int tolerance = 128;
	private static double kP = 5;
	private static final double encoderScale = 500;
	private static final double maxSpeed = 1;
	private static final double minSpeed = -1;

	public static void init() {
		encoder.reset();
		SmartDashboard.putNumber("kP", kP);
		SmartDashboard.putNumber("position", position);
	}

	public static void lock() {
		lockSpeed -= encoder.getRate();
		move(lockSpeed);
	}

	public static void move(double speed) {
		if (topLimit.get()) {
			speed = cap(speed, -1, 0);
		} else if(bottomLimit.get()) {
			speed = cap(speed, 0, 1);
		}
		elevator.set(speed);
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

	private static double cap(double value, double min, double max) {
		if (value > max) {
			value = max;
		} else if (value < min) {
			value = min;
		}
		return value;
	}
	public static void pControl() {
		kP = SmartDashboard.getNumber("kP");
		position = SmartDashboard.getNumber("position");
		double inputValue = encoder.get()/encoderScale;
		double rawError=position-inputValue;
		double output = -rawError*kP;
		output = cap(output, minSpeed, maxSpeed);
		move(output);
	}
}
