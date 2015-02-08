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
	static int[] positions = new int[] {234,2355,2533};
	private static double kP = 5;
	private static final double encoderScale = 512;

	public static void init() {
		encoder.reset();
		position = 0;
		SmartDashboard.putNumber("kP", kP);
		SmartDashboard.putNumber("position", position);
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
	
	public static void manualPosition(boolean direction) {
		if (direction) {
			position += 0.03;
		} else {
			position -= 0.04;
		}
	}
	public static void pControl() {
		double encoderValue = encoder.get()/encoderScale;
		SmartDashboard.putNumber("elevatorEncoder", encoderValue);
		SmartDashboard.putNumber("targetPosition", position);
		double rawError=position-encoderValue;
		double output = -rawError*kP;
		elevator.set(output);
		SmartDashboard.putNumber("elevatorOutput", output);
	}
}
