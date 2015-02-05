package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.PIDOutput;

public class Elevator {
	private static final VictorSP elevator = new VictorSP(Ports.elevator);
	private static final Encoder encoder = new Encoder(Ports.elevatorEncoder1, Ports.elevatorEncoder2);
	private static final DigitalInput topLimit = new DigitalInput(Ports.topLimit);
	private static final DigitalInput bottomLimit = new DigitalInput(Ports.bottomLimit);
	private static final PIDController pid = new PIDController(0.1, 0.1, 0.1, encoder, elevator);
	private static boolean manualMode = true;
	private static int position = 0;
	static int[] positions = new int[] {234,2355,2533};
	private static final int tolerance = 128;

	
	private class pidElevatorControl implements PIDOutput {
		public void pidWrite(double output) {
			if (topLimit.get() && output > 0) {
				move(0);
			} else if (bottomLimit.get() && output < 0) {
				move(0);
			} else {
				move(output);
			}
		}
	}
	
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
		pid.setAbsoluteTolerance(tolerance);
		pid.setSetpoint(position);
	}
}
