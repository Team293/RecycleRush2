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
	
	public boolean isToggled() {
		m_current = m_joystick.getRawButton(m_buttonNumber);
		if (m_current && !m_previous) {
			m_toggle = !m_toggle;
		}
		m_previous = m_current;
		return m_toggle;
	}
	

}
