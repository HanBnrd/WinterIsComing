package achievement2;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class DisplayPosition implements Behavior {
	MovePilot pilot;
	
	public DisplayPosition() {
		// TODO Auto-generated constructor stub
	}

	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Button.ESCAPE.isDown();
	}

	public void action() {
		// TODO Auto-generated method stub
		LCD.clear();
		LCD.drawString(Map.POSITION[0]+" "+" "+Map.POSITION[1], 0, 3);
		LCD.refresh();
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
