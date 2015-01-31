package subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Encoder;

public class Elevator {
	private static final Talon elevator = new Talon(Ports.elevator);
	private static final Encoder encoder = new Encoder(Ports.elevatorEncoder1, Ports.elevatorEncoder2);
	private static int position = 0;
	static int[] counts = new int[] {234,2355,2533};
	private static final int tolerance = 128;
	
	
	public static void move(boolean direction) {
		if (direction) {
			elevator.set(1);
		} else {
			elevator.set(-1);
		}
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
