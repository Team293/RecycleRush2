package SpikeLibrary;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpikePIDRobotDrive{
	private double speed = 1;
	private RobotDrivePIDOutput robotDrivePIDOutput;
	private PIDGyro gyro;
	private PIDController pidController;
	private double iterativeSetpoint = 0;
	private double finalSetpoint = 0;
	private Encoder leftEncoder= new Encoder(Ports.leftDriveEncoder1,Ports.leftDriveEncoder2);
	private Encoder rightEncoder=new Encoder(Ports.rightDriveEncoder1, Ports.rightDriveEncoder2);
	//private Encoder centerEncoder=new Encoder(Ports.centerDriveEncoder1,Ports.centerDriveEncoder2);
	private class RobotDrivePIDOutput implements PIDOutput {
		RobotDrive m_robotDrive;

		public RobotDrivePIDOutput(RobotDrive robotDrive) {
			m_robotDrive = robotDrive;
		}
		public void pidWrite(double output) {
			SmartDashboard.putNumber("pidOutput", output);
			if (Math.abs(iterativeSetpoint - finalSetpoint) < 0.0025) {
				iterativeSetpoint += 0.005;
			}
			pidController.setSetpoint(iterativeSetpoint);
			m_robotDrive.tankDrive(speed + output, speed - output);
		}
	}

	private class PIDGyro extends Gyro implements PIDSource{
		public PIDGyro(int port) {
			super(port);
		}

		public double pidGet() {
			double angle = getAngle();
			angle %= 360;
			angle /= 180; // range is now -1 to +1
			SmartDashboard.putNumber("gyro", angle);
			return angle;
		}
	}

	public SpikePIDRobotDrive(double Kp, double Ki, double Kd, int gyroPort, RobotDrive robotDrive) {
		robotDrivePIDOutput = new RobotDrivePIDOutput(robotDrive);
		gyro = new PIDGyro(gyroPort);
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

	public void reset() {
		gyro.reset();
		pidController.reset();
	}

	public void disable() {
		pidController.disable();
	}

	public void setDirection(double direction) {
		finalSetpoint = direction;
		iterativeSetpoint = gyro.pidGet();
	}

	public double getOutput() {
		return pidController.get();
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
	
/*public void SpikeTractionControl(){
	double centerencoder=centerEncoder.getRate();	
	double leftencoder=leftEncoder.getRate();
	double rightencoder=rightEncoder.getRate();
	SmartDashboard.putNumber("leftEncoder", leftencoder);
	SmartDashboard.putNumber("rightEncoder", rightencoder);
	SmartDashboard.putNumber("leftEncoder", centerencoder);
	double averageencoder=(leftencoder+rightencoder)/2;
	
	double differenceinencoder=averageencoder-centerencoder;
	
	if (differenceinencoder>.1||differenceinencoder<-.1){
		
	}
	
}*/


}


