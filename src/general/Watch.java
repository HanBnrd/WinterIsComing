package general;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Watch implements Behavior {
	MovePilot pilot;
	
	public Watch(MovePilot pilot) {
		// TODO Auto-generated constructor stub
		this.pilot = pilot;
	}

	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	public void action() {
		// TODO Auto-generated method stub
		LCD.clear();
		LCD.drawString("Watch", 0, 3);
		LCD.refresh();
		
		pilot.travel(8.8);
		switch (Map.POSITION[2])
		{
			case 0: Map.POSITION[0]--;
			break;
			case 1: Map.POSITION[1]++;
			break;
			case 2: Map.POSITION[0]++;
			break;
			case 3: Map.POSITION[1]--;
			break;
		}
        
        if (Map.POSITION[0] == 5 && Map.POSITION[0] == 0)
        {
        	if (Map.POSITION[2] == 2)
        	{
        		pilot.rotate(-90);
            	Map.POSITION[2] = 1;
        	}
        	else if (Map.POSITION[2] == 3)
        	{
        		pilot.rotate(90);
        		Map.POSITION[2] = 0;
        	}
        }
        if (Map.POSITION[0] == 0 && Map.POSITION[1] == 0)
		{
    		pilot.rotate(180);
    		Map.POSITION[2] = 2;
		}
        if (Map.POSITION[0] == 5 && Map.POSITION[1] == 3)
        {
        	pilot.rotate(180);
    		Map.POSITION[2] = 3;
        }
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
