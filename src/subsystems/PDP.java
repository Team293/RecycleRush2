package subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PowerDistributionPanel;


public class PDP {
	private static final PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public static void monitor() {
		/*SmartDashboard.putNumber("current0", pdp.getCurrent(0));
		SmartDashboard.putNumber("current1", pdp.getCurrent(1));*/
		SmartDashboard.putNumber("Mast", pdp.getCurrent(10));
		/*SmartDashboard.putNumber("current3", pdp.getCurrent(3));
		SmartDashboard.putNumber("current4", pdp.getCurrent(4));
		SmartDashboard.putNumber("current5", pdp.getCurrent(5));*/
		
	}

}
