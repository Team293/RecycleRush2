package autonomous;

import subsystems.DriveTrain;
import subsystems.Elevator;
import edu.wpi.first.wpilibj.Timer;

public class BinDriveStraight {
	
	static double time = 0;
	static int state = 0;
	static double distance = 0;
	static Timer autoTimer = new Timer();
	
	public static void init() {
		DriveTrain.enable();
	}
	
	public static void drive() {
		Elevator.reset();
		
		distance = DriveTrain.getAvgDistance();
		
		if (state == 0) {
			DriveTrain.tankDrive(.5, .5);
			state = 1;
		} 
		else if (state == 1 && distance > 6) {
			DriveTrain.stop();
			state = 2;
		} else if (state == 2) {
			Elevator.setPosition(4); //one tote position
			DriveTrain.resetEncoders();
			autoTimer.start();
			state = 3;
		} else if (state == 3 && distance > 120) {
			DriveTrain.stop();
			Elevator.setPresetPosition(1);
		}
	}
	
}
