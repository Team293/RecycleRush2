package SpikeLibrary;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class SpikeButton extends JoystickButton{

	
    GenericHID m_joystick;
    int m_buttonNumber;
    boolean m_toggle = false;
    boolean m_previous = false;
    boolean m_current = false;

	public SpikeButton(GenericHID joystick, int buttonNumber) {
		super(joystick, buttonNumber);
		m_joystick = joystick;
		m_buttonNumber = buttonNumber;
	}
	
	private void update() {
		m_previous = m_current;
		m_current = m_joystick.getRawButton(m_buttonNumber);
	}
	
	public boolean isHeld() {
		update();
		return m_current;
	}
	
	public boolean isBumped() {
		update();
		if (m_current && !m_previous) {
			return true;
		}
		return false;
	}
	
	public boolean isToggled() {
		update();
		if (isBumped()) {
			m_toggle = !m_toggle;
		}
		return m_toggle;
	}
	

}
