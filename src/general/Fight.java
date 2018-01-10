package general;

import general.Map;
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
		float squareSize = 88;
		int[] position = Map.POSITION;
		int[] whiteWalkerPosition = Map.WHITEWALKERPOSITION;
		System.out.println(whiteWalkerPosition[0]+" "+whiteWalkerPosition[1]);
		if (position[0] > whiteWalkerPosition[0])
		{
			switch (position[2])
			{
			case 1: pilot.rotate(-80);
			break;
			case 2: pilot.rotate(160);
			break;
			case 3: pilot.rotate(80);
			break;
			}
			position[2]=0;
			int nbSquare = position[0] - whiteWalkerPosition[0];
			pilot.travel(squareSize * (nbSquare));
		}
		else if (position[0] < whiteWalkerPosition[0])
		{
			switch (position[2])
			{
			case 0: pilot.rotate(160);
					
			break;
			case 1: pilot.rotate(80);
			break;
			case 3: pilot.rotate(-80);
			break;
			}
			position[2]=2;
			int nbSquare = whiteWalkerPosition[0]-position[0];
			pilot.travel(squareSize * (nbSquare));
		}
		if (position[1] > whiteWalkerPosition[1])
		{
			switch (position[2])
			{
			case 0: pilot.rotate(-80);
			break;
			case 1: pilot.rotate(160);
			break;
			case 2: pilot.rotate(80);
			break;
			}
			position[2]=3;
			int nbSquare = position[1] - whiteWalkerPosition[1];
			pilot.travel(squareSize * (nbSquare-2)); // to avoid hit
		}
		else if (position[1] < whiteWalkerPosition[1])
		{
			switch (position[2])
			{
			case 0: pilot.rotate(80);
			break;
			case 2: pilot.rotate(-80);
			break;
			case 3: pilot.rotate(160);
			break;
			}
			position[2]=1;
			int nbSquare = whiteWalkerPosition[1] - position[1];
			pilot.travel(squareSize * (nbSquare-2)); // to avoid hit
		}
		Map.WHITEWALKERPOSITION = null;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub
		
	}
}
