package achievement4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import general.Map;

public class BestPath {

	private ArrayList<Direction> path;
	
	public BestPath(PositionWithCost initialPos, Position finalPos) {
		path = new ArrayList<>();
		
	}
	
	public ArrayList<Direction> makePath() {
		PositionComparator pc = new PositionComparator();
		PriorityQueue<PositionWithCost> toVisit = new PriorityQueue<>(pc);
		ArrayList<Position> visited = new ArrayList<>();
		HashMap<Position, ArrayList<Position>> paths = new HashMap<>();
		
		
		
		ArrayList<Direction> res = positionsToDirections(new ArrayList<Position>());
		return res;
	}
	
	protected ArrayList<PositionWithCost> getSuccessors(PositionWithCost pos) {
		ArrayList<PositionWithCost> succs = new ArrayList<>();
		int[][] map = Map.map;
		int cost = pos.getCost();
		int x = pos.getPosX();
		int y = pos.getPosY();
		if (x > 0) {
			int newX = x-1;
			int newCost = cost + map[y][newX];
			succs.add(new PositionWithCost(newX, y, newCost));
		}
		if (pos.getPosX() < map[0].length) {
			int newX = x+1;
			int newCost = cost + map[y][newX];
			succs.add(new PositionWithCost(newX, y, newCost));
		}
		if (pos.getPosY() > 0) {
			int newY = y-1;
			int newCost = cost + map[newY][x];
			succs.add(new PositionWithCost(x, newY, newCost));
		}
		if  (pos.getPosY() < map.length) {
			int newY = y+1;
			int newCost = cost + map[newY][x];
			succs.add(new PositionWithCost(x, newY, newCost));
		}
		return succs;
	}
	
	
	public ArrayList<Direction> positionsToDirections(ArrayList<Position> positions) {
		ArrayList<Direction> directions = new ArrayList<>();
		for (Position pos : positions) {
			//todo
		}
		return directions;
	}
}
