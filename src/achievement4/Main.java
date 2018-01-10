package achievement4;

import java.util.ArrayList;

import general.Map;

public class Main {

	public static void main(String[] args) {
		Map.POSITION[0] = 6;
		Map.POSITION[1] = 0;
		BestPath bp = new BestPath(new PositionWithCost(6,0), new Position(2,5)); //Sansa = marcheur blanc , Cersei = garde de nuit
		ArrayList<Position> positions = bp.makePath();
		for (Position p: positions) {
			System.out.println(p.getPosX() + " | " + p.getPosY());
		}
	}

}
