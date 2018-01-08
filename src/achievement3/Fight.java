package achievement3;

import achievement2.*;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Fight implements Behavior
{
	boolean fight = false;
	int [] whiteWalkerPosition;
	MovePilot pilot;
	
	public Fight(MovePilot pilot)
	{
		this.pilot = pilot;
	}
	
	public void setFightMode(boolean fight)
	{
		this.fight = fight;
	}
	
	public void setWhiteWalkerPosition(int[] position)
	{
		whiteWalkerPosition = position;  
	}
	
	@Override
	public boolean takeControl()
	{
		// TODO Auto-generated method stub
		return fight;
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
			case 0: pilot.rotate(-90);
			break;
			case 2: pilot.rotate(90);
			break;
			case 3: pilot.rotate(180);
			break;
			}
			float x = whiteWalkerPosition[0] - position[0];
			pilot.travel(squareSize * x);
		}
		else if (position[0] > whiteWalkerPosition[0])
		{
			switch (position[2])
			{
			case 0: pilot.rotate(90);
			break;
			case 1: pilot.rotate(180);
			break;
			case 2: pilot.rotate(-90);
			break;
			}
			float x = position[0] - whiteWalkerPosition[0];
			pilot.travel(squareSize * x);
		}
		if (position[1] > whiteWalkerPosition[1])
		{
			switch (position[2])
			{
			case 1: pilot.rotate(-90);
			break;
			case 2: pilot.rotate(180);
			break;
			case 3: pilot.rotate(90);
			break;
			}
			float y = position[1] - whiteWalkerPosition[1];
			pilot.travel(squareSize * y);
		}
		else if (position[1] < whiteWalkerPosition[1])
		{
			switch (position[2])
			{
			case 0: pilot.rotate(180);
			break;
			case 1: pilot.rotate(90);
			break;
			case 3: pilot.rotate(-90);
			break;
			}
			float y = whiteWalkerPosition[1] - position[1];
			pilot.travel(squareSize * y);
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
}