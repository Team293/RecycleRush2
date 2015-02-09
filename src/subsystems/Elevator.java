package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import SpikeLibrary.SpikeMath;
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
	static int[] positions = new int[] {234,2355,2533};
	private static double kP = 0.68;
	private static final double encoderScale = 512; //counts per rotation
	private static final double circumference = 7.4; //of belt gear

	public static void init() {
		encoder.reset();
		position = 0;
		SmartDashboard.putNumber("kP", kP);
		SmartDashboard.putNumber("position", position);
	}




	public static void move(double speed) {
		//stops from moving through limits
		if (topLimit.get()) {
			speed = SpikeMath.cap(speed, 0, 1);
		} else if (bottomLimit.get()) {
			speed = SpikeMath.cap(speed, -1, 0);
		}
		elevator.set(speed);
	}
	
	public static void setMode(boolean newMode) {
		//sets whether is controlled manually or through preset positions
		manualMode = newMode;
	}

	public static boolean getMode() {
		//returns whether is controlled manually or through preset positions
		return manualMode;
	}

	public static void presetPosition(int positionInput) {
		//set the target position to a preset position
		position = positions[positionInput];
	}
	
	public static void manualPosition(boolean direction) {
		//change the target position manually
		if (direction) {
			position += 0.03;
		} else {
			position -= 0.04;
		}
	}
	
	public static double getInches() {
		double rotations = encoder.get()/encoderScale;
		double inches = rotations * circumference;
		return -inches;
	}
	
	public static void pControl() {
		//go to the target position
		double currentPosition = getInches();
		SmartDashboard.putNumber("elevatorEncoder", currentPosition);
		SmartDashboard.putNumber("targetPosition", position);
		double rawError = position-currentPosition;
		double output = rawError*kP;
		move(output);
		SmartDashboard.putNumber("elevatorOutput", output);
	}
}
