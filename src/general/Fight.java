package general;

import general.Map;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Fight implements Behavior
{
	MovePilot pilot;
	
	public Fight(MovePilot pilot)
	{
		this.pilot = pilot;
	}
	
	@Override
	public boolean takeControl()
	{
		// TODO Auto-generated method stub
		return Map.WHITEWALKERPOSITION != null;
	}

	@Override
	public void action()
	{
		// TODO Auto-generated method stub
		LCD.clear();
		LCD.drawString("Fight", 0, 3);
		LCD.refresh();
		
		if (Map.POSITION[0] < Map.WHITEWALKERPOSITION[0])
		{
			switch (Map.POSITION[2])
			{
			case 1: pilot.rotate(90);
			break;
			case 2: pilot.rotate(180);
			break;
			case 3: pilot.rotate(-90);
			break;
			}
			int nbSquare = Map.WHITEWALKERPOSITION[0] - Map.POSITION[0];
			pilot.travel(Util.SQUAREWIDTH * nbSquare);
		}
		else if (Map.POSITION[0] > Map.WHITEWALKERPOSITION[0])
		{
			switch (Map.POSITION[2])
			{
			case 0: pilot.rotate(180);
			break;
			case 1: pilot.rotate(-90);
			break;
			case 2: pilot.rotate(90);
			break;
			}
			int nbSquare = Map.POSITION[0] - Map.WHITEWALKERPOSITION[0];
			pilot.travel(Util.SQUAREWIDTH * nbSquare);
		}
		if (Map.POSITION[1] > Map.WHITEWALKERPOSITION[1])
		{
			switch (Map.POSITION[2])
			{
			case 0: pilot.rotate(-90);
			break;
			case 1: pilot.rotate(180);
			break;
			case 2: pilot.rotate(90);
			break;
			}
			int nbSquare = Map.POSITION[1] - Map.WHITEWALKERPOSITION[1];
			pilot.travel(Util.SQUAREWIDTH * nbSquare);
		}
		else if (Map.POSITION[1] < Map.WHITEWALKERPOSITION[1])
		{
			switch (Map.POSITION[2])
			{
			case 0: pilot.rotate(90);
			break;
			case 1: pilot.rotate(-90);
			break;
			case 3: pilot.rotate(180);
			break;
			}
			int nbSquare = Map.WHITEWALKERPOSITION[1] - Map.POSITION[1];
			pilot.travel(Util.SQUAREWIDTH * nbSquare);
		}
		Map.WHITEWALKERPOSITION = null;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
}
