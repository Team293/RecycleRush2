package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import SpikeLibrary.SpikeMath;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
	private static final VictorSP elevator = new VictorSP(Ports.elevator);
	private static final Encoder encoder = new Encoder(Ports.elevatorEncoder1, Ports.elevatorEncoder2);
	private static final DigitalInput topLimit = new DigitalInput(Ports.elevatorTopLimit);
	private static final DigitalInput bottomLimit = new DigitalInput(Ports.elevatorBottomLimit);
	private static boolean manualMode = true;
	private static double targetPosition = 0;
	static int[] positions = new int[] {234,2355,2533};
	private static double kP = 7;
	private static final double encoderScale = 512; //counts per rotation
	private static final double circumference = 7.4; //of belt gear

	public static void reset() {
		encoder.reset();
		targetPosition = 0;
	}

	public static void move(double speed) {
		//stops from moving through limits
		if (topLimit.get()) {
			speed = SpikeMath.cap(speed, 0, 1);
		} else if (bottomLimit.get()) {
			reset();
			speed = SpikeMath.cap(speed, -1, 0);
		}
		elevator.set(speed);
	}
	
	public static void setManualMode(boolean newMode) {
		//sets whether is controlled manually or through preset positions
		manualMode = newMode;
	}

	public static boolean getManualMode() {
		//returns whether is controlled manually or through preset positions
		return manualMode;
	}

	public static void setPresetPosition(int positionInput) {
		//set the target position to a preset position
		targetPosition = positions[positionInput];
	}
	
	public static void updateManualPosition(boolean direction) {
		//change the target position manually
		if (direction) {
			targetPosition += 0.01;
		} else {
			targetPosition -= 0.01;
		}
	}
	
	public static double getInches() {
		double rotations = encoder.get()/encoderScale;
		double inches = rotations * circumference;
		return -inches;
	}
	
	public static void periodicPControl() {
		//go to the target position
		double currentPosition = getInches();
		SmartDashboard.putNumber("currentPosition", currentPosition);
		SmartDashboard.putNumber("targetPosition", targetPosition);
		double rawError = targetPosition-currentPosition;
		double output = rawError*kP;
		elevator.set(output);
		SmartDashboard.putNumber("elevatorOutput", output);
	}
}
