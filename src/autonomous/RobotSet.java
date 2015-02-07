package autonomous;

import subsystems.*;

public class RobotSet extends Auto {
	public RobotSet() {
		super();
	}
	
	public void init() {
		super.init();
	}
	
	public void run() {
		DriveTrain.setPIDSpeed(0);
		DriveTrain.setPIDDirection(0.5);
		DriveTrain.pidEnable(true);
/*		if (autoTimer.get() < driveToAutozoneTime) {
			DriveTrain.pidEnable(true);
		}*/
	}
}
