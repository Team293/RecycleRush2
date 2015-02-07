package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import SpikeLibrary.SpikePIDRobotDrive;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain {
	private static final double diameter = 10;

    private static final Encoder leftEncoder = new Encoder(Ports.leftDriveEncoder1, Ports.leftDriveEncoder2);
    private static final Encoder rightEncoder = new Encoder(Ports.rightDriveEncoder1, Ports.rightDriveEncoder2);
    
    private static final Talon leftMotor = new Talon(Ports.leftDrive);
    private static final Talon rightMotor = new Talon(Ports.rightDrive);
    
    private static final RobotDrive drive = new RobotDrive(leftMotor, rightMotor);
    private static final SpikePIDRobotDrive pidDrive = new SpikePIDRobotDrive(0.5, 0, 0.05, Ports.gyro, drive);
 
    public static void init() {
    	pidDrive.reset();
    }
	public static void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	public static void arcadeDrive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}
	
	public static void pidEnable(boolean state) {
		pidDrive.enable(state);
	}
	
	public static void setPIDSpeed(double speed) {
		pidDrive.setSpeed(speed);
	}
	
	public static void setPIDDirection(double direction) {
		pidDrive.setDirection(direction);
		pidDrive.setPID(SmartDashboard.getNumber("DriveP"), SmartDashboard.getNumber("DriveI"), SmartDashboard.getNumber("DriveD"));
	}
	
	public static double convertToDistance(double count) {
		double rotations  = count/256;
		double distance = Math.PI*diameter*rotations;
		
		return distance;
	}
	
	public static double getDistance() {
		return convertToDistance((leftEncoder.get() + rightEncoder.get())/2);
	}
}
