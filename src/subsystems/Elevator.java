package subsystems;

import java.util.ArrayList;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Talon;

public class Elevator {
	public static final Talon elevator = new Talon(Ports.elevator);
	private static final int position = 0;
	private static final ArrayList<Integer> positions = new ArrayList<Integer>();
	Int[] positions;
	positions = new Int[] {234,2355,2533};
	
	String[] auto;
	auto = new String[]{"asdf","asdfs","asdf"};
	
	
	public static void move(boolean direction) {
		if (direction) {
			elevator.set(1);
		} else {
			elevator.set(-1);
		}
	}
	
	public static void goToPosition() {
		
	}

}
