package general;

import achievement2.*;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Fight implements Behavior
{
	int [] whiteWalkerPosition;
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
		float squareSize = 8;
		int[] position = Map.POSITION;
		if (position[0] < whiteWalkerPosition[0])
		{
			switch (position[2])
			{
			case 1: pilot.rotate(90);
			break;
			case 2: pilot.rotate(180);
			break;
			case 3: pilot.rotate(-90);
			break;
			}
			int nbSquare = whiteWalkerPosition[0] - position[0];
			pilot.travel(squareSize * nbSquare);
		}
		else if (position[0] > whiteWalkerPosition[0])
		{
			switch (position[2])
			{
			case 0: pilot.rotate(180);
			break;
			case 1: pilot.rotate(-90);
			break;
			case 2: pilot.rotate(90);
			break;
			}
			int nbSquare = position[0] - whiteWalkerPosition[0];
			pilot.travel(squareSize * nbSquare);
		}
		if (position[1] > whiteWalkerPosition[1])
		{
			switch (position[2])
			{
			case 0: pilot.rotate(-90);
			break;
			case 1: pilot.rotate(180);
			break;
			case 2: pilot.rotate(90);
			break;
			}
			int nbSquare = position[1] - whiteWalkerPosition[1];
			pilot.travel(squareSize * nbSquare);
		}
		else if (position[1] < whiteWalkerPosition[1])
		{
			switch (position[2])
			{
			case 0: pilot.rotate(90);
			break;
			case 1: pilot.rotate(-90);
			break;
			case 3: pilot.rotate(180);
			break;
			}
			int nbSquare = whiteWalkerPosition[1] - position[1];
			pilot.travel(squareSize * nbSquare);
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
}
