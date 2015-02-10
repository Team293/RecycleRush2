package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm {
	public static final Talon arm = new Talon(Ports.arm);
	private static final AnalogPotentiometer pot = new AnalogPotentiometer(Ports.armPot, 2, -1);
	private static double targetPosition = 0;
	static double[] positions = new double[] {0,0.25,0.75};
	private static final double kP = 5;
	
	public static void move(double speed) {
		arm.set(speed);
	}
	
	public static void setPresetPosition(int positionInput) {
		targetPosition = positions[positionInput];
	}
	
	public static void setPosition(double positionInput) {
		targetPosition = positionInput;
	}
	
	public static void periodicControl() {
		SmartDashboard.putNumber("potValue", pot.get());
		double error = pot.get() - targetPosition;
		SmartDashboard.putNumber("armSpeed", kP * error);
		move(kP * error);
	}



}
