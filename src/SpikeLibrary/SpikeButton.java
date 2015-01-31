package SpikeLibrary;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class SpikeButton extends JoystickButton{

	
    GenericHID joystick;
    int buttonNumber;
    boolean toggle = false;
    boolean previous = false;
    boolean current = false;

	public SpikeButton(GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);
		this.joystick = joystick;
		this.buttonNumber = buttonNumber;
	}
	
	private void update() {
		previous = current;
		current = joystick.getRawButton(buttonNumber);
	}
	
	public boolean isHeld() {
		update();
		return current;
	}
	
	public boolean isBumped() {
		update();
		if (current && !previous) {
			return true;
		}
		return false;
	}
	
	public boolean isToggled() {
		update();
		if (isBumped()) {
			toggle = !toggle;
		}
		return toggle;
	}
	

}
