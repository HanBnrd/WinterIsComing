package achievement2;

import general.Map;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class DisplayPosition implements Behavior {

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
