package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Talon;

public class Elevator {
	public static final Talon elevator = new Talon(Ports.elevator);
	public static int position = 0;
	
	public static void move(boolean direction) {
		if (direction) {
			elevator.set(1);
		} else {
			elevator.set(-1);
		}
	}

}
