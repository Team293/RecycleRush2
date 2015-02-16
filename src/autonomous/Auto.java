package autonomous;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Timer;

public class Auto {
	
	private static int state;
	private double deltaLeftGyroFB = 0;
	private double deltaRightGyroFB = 0;
	private double Kp = .3; // to be determined from testing
	
	
	private void angleTracker (Gyro gyro, double desiredAngle) {
		double error; // angle error between desired and measured
		
		error = desiredAngle - gyro.getAngle();
		
		deltaRightGyroFB =  error * Kp;
		deltaLeftGyroFB  = -error * Kp;
	}

}
