package autonomous;

import subsystems.DriveTrain;
import subsystems.Elevator;

public class ToteDriveStraight {
	static double distance = 0;
	static int state = 0;
	
	public static void init() {
		DriveTrain.enable();
	}
	
	public static void drive() {
		distance = DriveTrain.getAvgDistance();
		
		if (state == 0)  {
			DriveTrain.tankDrive(.5, .5);
			state = 1;
		} else if (state == 1) {
			DriveTrain.disable();
			Elevator.setPosition(0);
			DriveTrain.resetEncoders();
		}
	} 
} 
