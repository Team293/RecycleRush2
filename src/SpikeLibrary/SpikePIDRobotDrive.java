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
	private RobotDrivePIDOutput robotDrivePIDOutput;
	private UnitGyro gyro;
	private PIDController pidController;

	private class RobotDrivePIDOutput implements PIDOutput {
		RobotDrive m_robotDrive;

		public RobotDrivePIDOutput(RobotDrive robotDrive) {
			m_robotDrive = robotDrive;
		}
		public void pidWrite(double output) {
			m_robotDrive.drive(speed, output);
		}
	}

	private class UnitGyro extends Gyro implements PIDSource{
		public UnitGyro(int port) {
			super(port);
		}

		public double pidGet() {
			double angle = getAngle();
			angle %= 360;
			angle -= 360;
			angle /= 180;				// range is now -1 to +1
			return -angle;
		}
	}

	public SpikePIDRobotDrive(double Kp, double Ki, double Kd, int gyroPort, RobotDrive robotDrive) {
		robotDrivePIDOutput = new RobotDrivePIDOutput(robotDrive);
		gyro = new UnitGyro(gyroPort);
		pidController = new PIDController(Kp, Ki, Kd, gyro, robotDrivePIDOutput);
		pidController.setSetpoint(0);
		pidController.setInputRange(-1, 1);
		pidController.setOutputRange(-1, 1);
		pidController.setAbsoluteTolerance(0.03);
		pidController.setContinuous();

	}

	public void enable(boolean state) {
		if (state) {
			pidController.enable();
		} else {
			pidController.disable();
		}
	}
	
	public void disable() {
		pidController.disable();
	}

	public void setDirection(double direction) {
		pidController.setSetpoint(direction);
	}
	
	public void setSpeed(double speedInput) {
		speed = speedInput;
	}

	public void setPID(double p, double i, double d) {
		pidController.setPID(p, i, d);
	}
	
	public void setTolerance(double tolerance) {
		pidController.setAbsoluteTolerance(tolerance);
	}
	
	public boolean onTarget() {
		return pidController.onTarget();
	}


}
