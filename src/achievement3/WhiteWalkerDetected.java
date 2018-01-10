package achievement3;

import java.util.ArrayList;

import achievement4.BestPath;
import achievement4.Position;
import achievement4.PositionWithCost;
import general.Map;
import general.Util;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class WhiteWalkerDetected implements Behavior
{
	MovePilot pilot;
	
	public WhiteWalkerDetected(MovePilot pilot) {
		// TODO Auto-generated constructor stub
		this.pilot = pilot;
	}

	@Override
	public boolean takeControl() {
		// TODO Auto-generated method stub
		return Map.WHITEWALKERPOSITION != null;
	}

	@Override
	public void action() {
		// TODO Auto-generated method stub
		BestPath bp = new BestPath(new PositionWithCost(Map.POSITION[0], Map.POSITION[1]), new Position(Map.WHITEWALKERPOSITION[0], Map.WHITEWALKERPOSITION[0]));
		ArrayList<Position> positions = bp.makePath();
		for (Position position : positions)
		{
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
				pilot.travel(Util.SQUAREWIDTH);
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
				pilot.travel(Util.SQUAREWIDTH);
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
				pilot.travel(Util.SQUAREWIDTH);
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
				pilot.travel(Util.SQUAREWIDTH);
			}
		}
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
