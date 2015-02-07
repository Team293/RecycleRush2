package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
	private static final VictorSP elevator = new VictorSP(Ports.elevator);
	private static final Encoder encoder = new Encoder(Ports.elevatorEncoder1, Ports.elevatorEncoder2);
	private static final double encoderscale=500;
	private static final float kp = 5;  //default value for kp
	private static final int[] positions = int
	
	public static void init() {
		encoder.reset();
		SmartDashboard.putNumber("Setpoint", 0);
		SmartDashboard.putNumber("KP", kp);
	}
	
	public static void move(double speed) {
		elevator.set(speed);
	}
	
	public static void pControl() {
		double inputvalue = encoder.get()/encoderscale;
		double setpoint = (double) SmartDashboard.getNumber("Setpoint");
		SmartDashboard.putNumber("Encoder", inputvalue);
		double kp= (double) SmartDashboard.getNumber("KP");
		double rawerror=setpoint-inputvalue;
		
		double output = -rawerror*kp;
		if (output > 1){
			output = 1;
		} else if (output < -1){
			output = -1;
		}
		move(output);
		SmartDashboard.putNumber("Motor", output);
	}
}
