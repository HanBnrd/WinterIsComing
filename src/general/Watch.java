package general;

import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Watch implements Behavior {
	private MovePilot pilot;
	
	/**
	 * Constructeur du comportement permettant de faire une patrouille en L entre 2 campements pour la garde de nuit 
	 * @param pilot l'instance de MovePilot associee au robot garde de nuit
	 */
	public Watch(MovePilot pilot) {
		// TODO Auto-generated constructor stub
		this.pilot = pilot;
	}

	public boolean takeControl() {
		// TODO Auto-generated method stub
		return true;
	}

	public void action()
	{
		// TODO Auto-generated method stub
		
		pilot.travel(Util.SQUAREWIDTH);
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
