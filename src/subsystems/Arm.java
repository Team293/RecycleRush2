package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

public class Arm {
	public static final Talon arm = new Talon(Ports.arm);
	private static final Encoder encoder = new Encoder(Ports.armEncoder1, Ports.armEncoder2,false, Encoder.EncodingType.k4X);
	private static boolean manualMode = true;
	private static int position = 0;
	static int[] counts = new int[] {234,2355,2533};
	private static final int tolerance = 128;
	
	public static void move(double speed) {
		arm.set(speed);
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
	
	public static int getDestinationPosition() {
		return position;
	}
	
	public static void goToPosition() {
		int error = getError(encoder.get(), counts[position]);
		if(Math.abs(error) > tolerance) {
			move(error/tolerance/2);
		}
	}



}
