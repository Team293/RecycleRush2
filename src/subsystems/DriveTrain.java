package subsystems;

import org.usfirst.frc.team293.robot.Ports;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;

public class DriveTrain {
	private static final double diameter = 10;
	private static final double width = 40;
	
    private static final VictorSP leftMotor1 = new VictorSP(Ports.leftDrive1);
    private static final VictorSP rightMotor1 = new VictorSP(Ports.rightDrive1);
    private static final VictorSP leftMotor2 = new VictorSP(Ports.leftDrive2);
    private static final VictorSP rightMotor2 = new VictorSP(Ports.rightDrive2);

    
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
