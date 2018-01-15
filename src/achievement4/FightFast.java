package achievement4;

import java.util.ArrayList;

import general.Map;
import general.Util;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class FightFast implements Behavior
{
	MovePilot pilot;
	
	public FightFast(MovePilot pilot) {
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
		LCD.clear();
		LCD.drawString("Chemin le + court", 0, 2);
		LCD.refresh();
		BestPath bp = new BestPath(new PositionWithCost(Map.POSITION[0], Map.POSITION[1]), new Position(Map.WHITEWALKERPOSITION[0], Map.WHITEWALKERPOSITION[1]));
		ArrayList<Position> positions = bp.makePathUCSbis();
		int valuePos;
		positions.remove(0);
		for (Position position : positions)
		{
			valuePos = Map.map[Map.POSITION[0]][Map.POSITION[1]];
			switch(valuePos) {
			case 1:
				Delay.msDelay(500);
				break;
			case 5:
				Delay.msDelay(2500);
				break;
			case 10:
				Delay.msDelay(5000);
				break;
			}
			if (Map.POSITION[0] > position.getPosX())
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
				Map.POSITION[2] = 0;
				pilot.travel(Util.SQUAREWIDTH);
				Map.POSITION[0] -= 1;
			}
			else if (Map.POSITION[0] < position.getPosX())
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
				Map.POSITION[2] = 2;
				pilot.travel(Util.SQUAREWIDTH);
				Map.POSITION[0] += 1;
			}
			if (Map.POSITION[1] > position.getPosY())
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
				Map.POSITION[2] = 3;
				pilot.travel(Util.SQUAREWIDTH);
				Map.POSITION[1] -= 1;
			}
			else if (Map.POSITION[1] < position.getPosY())
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
				Map.POSITION[2] = 1;
				pilot.travel(Util.SQUAREWIDTH);
				Map.POSITION[1] += 1;
			}
		}
		Map.WHITEWALKERPOSITION = null;
	}

	@Override
	public void suppress() {
		// TODO Auto-generated method stub

	}

}
