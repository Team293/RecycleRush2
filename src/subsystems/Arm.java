package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import SpikeLibrary.SpikeMath;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	public static final Talon arm = new Talon(Ports.arm);
	private static final double min = -0.36;
	private static final double max = 0.73;
	private static final AnalogPotentiometer pot = new AnalogPotentiometer(Ports.armPot, 2, -1);
	private static double targetPosition = 0;
	static double[] positions = new double[] {0,0.25,0.75};
	private static final double kP = 2;
	
	public static void move(double speed) {
		if (pot.get() > max) {
			SpikeMath.cap(speed, -1, 0);
		} else if (pot.get() < min) {
			SpikeMath.cap(speed, 0, 1);
		}
		arm.set(-speed);
	}
	
	public static void viewPosition() {
		SmartDashboard.putNumber("armPosition", pot.get());
	}
	
	public static void setPresetPosition(int positionInput) {
		targetPosition = positions[positionInput];
	}
	
	public static void setPosition(double positionInput) {
		targetPosition = positionInput;
	}
	
	private static void updateManualPosition(boolean direction) {
		if (direction) {
			targetPosition += 0.005;
		} else {
			targetPosition -= 0.005;
		}
	}
	
	public static void periodicControl() {
		SmartDashboard.putNumber("potValue", pot.get());
		double error = pot.get() - targetPosition;
		SmartDashboard.putNumber("armSpeed", kP * error);
		move(kP * error);
	}



}
