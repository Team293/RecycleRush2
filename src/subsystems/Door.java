package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Door {
	private static final DoubleSolenoid door = new DoubleSolenoid(Ports.door1, Ports.door2);

	public static void move(boolean direction) {
		if (direction) {
			door.set(DoubleSolenoid.Value.kForward);
		} else {
			door.set(DoubleSolenoid.Value.kReverse);
		}
	}
	

}