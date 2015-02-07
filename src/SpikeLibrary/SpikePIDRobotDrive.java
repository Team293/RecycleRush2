package SpikeLibrary;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;

public class SpikePIDRobotDrive{
	private static double speed = 1;
	private static double direction = 0;
	private PIDController pidController;
	private PIDRobotDrive pidRobotDrive;
	private Gyro gyro;
	private PIDGyro pidGyro;
	private RobotDrive robotDrive;
	
	private class PIDRobotDrive implements PIDOutput {
		public void pidWrite(double output) {
			robotDrive.drive(speed, output);
		}
	}
	private class PIDGyro extends Gyro {
		
		public PIDGyro(Gyro gyro) {
			super(gyro.g);
		}

		public double pidGet() {
			double angle = gyro.getAngle();	// angle could be > 360, or -ve
			angle %= 360;				// angle will be positive after modulo
			if (angle > 180)			// range is 0 to 360
				angle -= 360;			// range is now -180 to +180
			angle /= 180;				// range is now -1 to +1
			return -angle;
		}
	}

	public SpikePIDRobotDrive(double Kp, double Ki, double Kd, PIDSource gyro, RobotDrive robotDrive) {
		pidGyro = new PIDGyro(gyro);
		pidController = new PIDController(Kp, Ki, Kd, PIDGyro, PIDRobotDrive);
	}	
}
