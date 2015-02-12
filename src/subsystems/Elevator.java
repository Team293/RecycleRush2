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
	private static final double PICKUP = 0.75;
	private static final double TRAVEL = 4.25;
	private static final double ONETOTE = 15.25;
	private static final double CANONTOTE = 19.5;
	private static final double TWOTOTE = 27.5;
	private static final double CANONTWOTOTE = 31.5;
	static double[] positions = new double[] {PICKUP, TRAVEL, ONETOTE, CANONTOTE, TWOTOTE, CANONTWOTOTE};
	private static double kP = 1.19;
	private static final double encoderScale = 512; //counts per rotation
	private static final double circumference = 7.56; //of belt gear


	public static void reset() {
		encoder.reset();
		targetPosition = 0;
	}

	public static void move(double speed) {
		//stops from moving through limits
		if (topLimit.get()) {
			speed = SpikeMath.cap(speed, -1, 0);
		} else if (bottomLimit.get()) {
			reset();
			speed = SpikeMath.cap(speed, 0, 1);
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
			targetPosition += 0.126;
		} else {
			targetPosition -= 0.126;
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
		double rawError = targetPosition - currentPosition;
		double output = rawError * kP;
		elevator.set(output);
		SmartDashboard.putNumber("elevatorOutput", output);
	}
}
