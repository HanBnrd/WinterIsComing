package general;

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
		pilot.travel(8.8);
		if (Map.POSITION[2] == 0)
			Map.POSITION[0]--;
		else if (Map.POSITION[2] == 1)
			Map.POSITION[1]++;
		else if (Map.POSITION[2] == 2)
			Map.POSITION[0]++;
		else if (Map.POSITION[2] == 3)
			Map.POSITION[1]--;
        
        if (Map.POSITION[0] == 5 && Map.POSITION[0] == 0)
        {
        	if (Map.POSITION[2] == 2)
        	{
        		pilot.rotate(-85);
            	Map.POSITION[2] = 1;
        	}
        	else if (Map.POSITION[2] == 3)
        	{
        		pilot.rotate(85);
        		Map.POSITION[2] = 0;
        	}
        }
        if (Map.POSITION[0] == 0 && Map.POSITION[1] == 0)
		{
    		pilot.rotate(175);
    		Map.POSITION[2] = 2;
		}
        if (Map.POSITION[0] == 5 && Map.POSITION[1] == 3)
        {
        	pilot.rotate(175);
    		Map.POSITION[2] = 3;
        }
	}

	public void suppress() {
		// TODO Auto-generated method stub
	}
}
