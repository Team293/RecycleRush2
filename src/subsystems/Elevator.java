package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class Elevator {
	private static final VictorSP elevator = new VictorSP(Ports.elevator);
	private static final Encoder encoder = new Encoder(Ports.elevatorEncoder1, Ports.elevatorEncoder2);
	private static final DigitalInput topLimit = new DigitalInput(Ports.topLimit);
	private static final DigitalInput bottomLimit = new DigitalInput(Ports.bottomLimit);
	private static boolean manualMode = true;
	private static int position = 0;
	static int[] positions = new int[] {234,2355,2533};
	private static final int tolerance = 128;
	
	public static void move(double speed) {
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

	public static void goToPosition() {
		double speed = Math.signum(position - encoder.get());
		if (Math.abs(position - encoder.get()) < tolerance) {
			speed = 0;
		}
		if (topLimit.get() && speed > 1) {
			speed = 0;
		} else if (bottomLimit.get() && speed < 1) {
			speed = 0;
		}
		
		move(speed);
	}
}
