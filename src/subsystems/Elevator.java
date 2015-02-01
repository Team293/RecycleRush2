package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;

public class Elevator {
	private static final VictorSP elevator = new VictorSP(Ports.elevator);
	private static final Encoder encoder = new Encoder(Ports.elevatorEncoder1, Ports.elevatorEncoder2);
	private static boolean manualMode = true;
	private static int position = 0;
	static int[] counts = new int[] {234,2355,2533};
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
	
	private static int getError(int num, int center) {
		int error = num - center;
		return error;
	}
	
	public static void setPosition(int positionInput) {
		position = positionInput;
	}
	
	public static void goToPosition() {
		int error = getError(encoder.get(), counts[position]);
		if(Math.abs(error) > tolerance) {
			elevator.set(error/tolerance/2);
		}
	}

}
