package SpikeLibrary;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SpikeLimit extends DigitalInput{
	
    boolean toggle = false;
    boolean previous = false;
    boolean current = false;

	
	public SpikeLimit(int port) {
		super(port);
	}
	
	private void update() {
		previous = current;
		current = get();
	}
	
	public boolean isHeld() {
		update();
		return current;
	}
	
	public boolean isBumped() {
		update();
		SmartDashboard.putBoolean("current", current);
		SmartDashboard.putBoolean("previous", previous);
		if (current && (!previous)) {
			return true;
		}
		return false;
	}
	
	public boolean isToggled() {
		if (isBumped()) {
			toggle = !toggle;
		}
		return toggle;
	}
}
