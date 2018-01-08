package achievement4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.PriorityQueue;

import achievement4.Enums.Directions;
import achievement4.Enums.Moves;
import general.Map;

public class BestPath {
	
	private PositionWithCost initPos;
	private Position finalPos;
	
	public BestPath(PositionWithCost init, Position fin) {
		initPos = init;
		finalPos = fin;
	}
	
	public ArrayList<Position> makePath() {
		PositionComparator pc = new PositionComparator();
		PriorityQueue<PositionWithCost> toVisit = new PriorityQueue<>(pc);
		ArrayList<Position> visited = new ArrayList<>();
		Hashtable<PositionWithCost, ArrayList<Position>> paths = new Hashtable<>();
		paths.put(initPos, new ArrayList<Position>());
		
		ArrayList<PositionWithCost> successors = getSuccessors(initPos);
		ArrayList<Position> pathSuccs;
		for(PositionWithCost succ : successors) {
			pathSuccs = new ArrayList<>();
			pathSuccs.add(new Position(initPos));
			paths.put(succ, pathSuccs);
		}
		
		boolean found = false;
		PositionWithCost item = null;
		Position itemPos;
		toVisit.add(initPos);
		while (!toVisit.isEmpty() && !found) {
			item = toVisit.poll();
			if(!alreadyVisited(item, visited)){
				if(isFinal(item)) {
					found = true;
				} else {
					itemPos = new Position(item);
					
					visited.add(itemPos);
					successors = getSuccessors(item);
					for(PositionWithCost succ : successors) {
						pathSuccs = new ArrayList<>(paths.get(item));
						pathSuccs.add(itemPos);
						paths.put(succ, pathSuccs);
						toVisit.add(succ);
					}
				}
			}
		}
		return paths.get(item);
	}
	
	private boolean isFinal(PositionWithCost pwc) {
		int pwX = pwc.getPosX(), pwY = pwc.getPosY(), fX = finalPos.getPosX(), fY = finalPos.getPosY();
		return (pwX-1 == fX && pwY == fY) ||
			   (pwX+1 == fX && pwY == fY) ||
			   (pwY-1 == fY && pwX == fX) ||
			   (pwY+1 == fY && pwX == fX) ;
	}
	
	private boolean alreadyVisited(PositionWithCost item, ArrayList<Position> positions) {
		boolean found = false;
		ListIterator<Position> ite = positions.listIterator();
		Position pos;
		while(ite.hasNext() && !found) {
			pos = ite.next();
			if(pos.equals(item)){
				found = true;
			}
		}
		return found;
	}

	private ArrayList<PositionWithCost> getSuccessors(PositionWithCost pos) {
		ArrayList<PositionWithCost> succs = new ArrayList<>();
		int[][] map = Map.map;
		int cost = pos.getCost();
		int x = pos.getPosX();
		int y = pos.getPosY();
		if (x > 0) {
			int newX = x-1;
			int newCost = cost + map[newX][y];
			if (!finalPos.equals(newX, y))
				succs.add(new PositionWithCost(newX, y, newCost));
		}
		if (x < map.length-1) {
			int newX = x+1;
			int newCost = cost + map[newX][y];
			if (!finalPos.equals(newX, y))
				succs.add(new PositionWithCost(newX, y, newCost));
		}
		if (y > 0) {
			int newY = y-1;
			int newCost = cost + map[x][newY];
			if (!finalPos.equals(x, newY))
				succs.add(new PositionWithCost(x, newY, newCost));
		}
		if  (y < map[0].length-1) {
			int newY = y+1;
			int newCost = cost + map[x][newY];
			if (!finalPos.equals(x, newY))
				succs.add(new PositionWithCost(x, newY, newCost));
		}
		return succs;
	}
	
	private Directions posToDir(Position p1, Position p2) {
		int p1X = p1.getPosX(), p1Y = p1.getPosY(), p2X = p2.getPosX(), p2Y = p2.getPosY();
		if (p1X+1 == p2X && p1Y == p2Y) 
			return Directions.DOWN;
		else {
			if (p1X-1 == p2X && p1Y == p2Y) 
				return Directions.UP;
			else {
				if (p1Y+1 == p2Y && p1X == p2X) {
					return Directions.RIGHT;
				}
			}
		}
		return Directions.LEFT;
	}
}
