package general;

import general.Map;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Fight implements Behavior
{
	private MovePilot pilot;
	
	/**
	 * Constructeur du comportement permettant a la garde de nuit de se rapprocher du marcheur blanc sans prendre en compte le cout
	 * Le mouvement se fait d'abord en hauteur puis en largeur
	 * @param pilot l'instance de MovePilot du robot garde de nuit
	 */
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
		if (position[0] > whiteWalkerPosition[0])
		{
			switch (Map.POSITION[2])
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
			switch (Map.POSITION[2])
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
		if (Map.POSITION[1] > Map.WHITEWALKERPOSITION[1])
		{
			switch (Map.POSITION[2])
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
		else if (Map.POSITION[1] < Map.WHITEWALKERPOSITION[1])
		{
			switch (Map.POSITION[2])
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
