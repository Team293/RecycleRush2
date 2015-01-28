package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.RobotDrive;

public class DriveTrain {
	private static final double diameter = 10;
	private static final double width = 40;
	
    private static final Talon leftMotor1 = new Talon(Ports.leftDrive1);
    private static final Talon rightMotor1 = new Talon(Ports.rightDrive1);
    private static final Talon leftMotor2 = new Talon(Ports.leftDrive2);
    private static final Talon rightMotor2 = new Talon(Ports.rightDrive2);

    
    private static final RobotDrive drive = new RobotDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
	

 
	public static void tankDrive(double leftSpeed, double rightSpeed) {
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	
	public static void arcadeDrive(double move, double rotate) {
		drive.arcadeDrive(move, rotate);
	}
	
	public static double convertToDistance(double count) {
		double rotations  = count/256;
		double distance = Math.PI*diameter*rotations;
		
		return distance;
	}
}
