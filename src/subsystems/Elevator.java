package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
	private static final VictorSP elevator = new VictorSP(Ports.elevator);
	private static final Encoder encoder = new Encoder(Ports.elevatorEncoder1, Ports.elevatorEncoder2);
	private static float integralError = 0;
	private static final float encoderscale=500;
	
	private static final float kp=5;  //default value for kp
	
	public static void init() {
		encoder.reset();
		SmartDashboard.putNumber("Setpoint", 0);
		SmartDashboard.putNumber("KP", kp);
	}
	
	public static void settosetpoint(){
		float inputvalue=encoder.get()/encoderscale;
		float setpoint=(float) SmartDashboard.getNumber("Setpoint");
		SmartDashboard.putNumber("Encoder", inputvalue);
		float kp= (float) SmartDashboard.getNumber("KP");
		float rawerror=setpoint-inputvalue;
		integralError += rawerror;
		if (integralError>.3){
			integralError=(float) .3;
		}
		else if (integralError<-.3){
			integralError=(float)-.3;
		}
		
		
		float output=-(rawerror*kp);
		if (output>1){
			output=(float) 1;
		}
		else if (output<-1){
			output=(float)-1;
		}
		elevator.set(output);
		SmartDashboard.putNumber("Motor", output);
	}
}
