package subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;


public class PDP {
	private static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public static void monitor() {
		SmartDashboard.putNumber("current0", pdp.getCurrent(0));
		SmartDashboard.putNumber("current0", pdp.getCurrent(1));
		SmartDashboard.putNumber("current0", pdp.getCurrent(2));
		SmartDashboard.putNumber("current0", pdp.getCurrent(3));
		SmartDashboard.putNumber("current0", pdp.getCurrent(4));
		SmartDashboard.putNumber("current0", pdp.getCurrent(5));
		
	}

}
