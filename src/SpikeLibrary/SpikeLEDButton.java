package SpikeLibrary;

import edu.wpi.first.wpilibj.Joystick;

public class SpikeLEDButton extends SpikeButton{

	private static int outputNumber;
	private static int periodLength = 25;
	private static int periodCounter = 0;
	private static boolean flashState = false;
	
	public SpikeLEDButton(Joystick joystick, int buttonNumber, int outputNumber) {
		super(joystick, buttonNumber);
		this.outputNumber = outputNumber;
		this.joystick = joystick;

	}
	
	public void setOutput(boolean state) {
		joystick.setOutput(outputNumber, state);
	}
	
	public void flash() {
		periodCounter += 1;
		if (periodCounter > periodLength) {
			periodCounter = 0;
			flashState = !flashState;
			setOutput(flashState);
		}
	}

}
