package achievement4;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.ListIterator;
import java.util.PriorityQueue;

import general.Map;

public class BestPath {
	
	private PositionWithCost initPos;
	private Position finalPos;
	
	public BestPath(PositionWithCost init, Position fin) {
		initPos = init;
		finalPos = fin;
	}
	
	public ArrayList<Position> makePathUCSbis() {
		ArrayList<PositionWithCost> toVisit1 = new ArrayList<>();
		ArrayList<PositionWithCost> toVisit5 = new ArrayList<>();
		ArrayList<PositionWithCost> toVisit10 = new ArrayList<>();
		ArrayList<Position> visited = new ArrayList<>();
		Hashtable<PositionWithCost, ArrayList<Position>> paths = new Hashtable<>();
		toVisit1.add(initPos);
		paths.put(initPos, new ArrayList<Position>());
		
		ArrayList<PositionWithCost> successors;
		ArrayList<Position> pathSuccs;
		
		boolean found = false;
		PositionWithCost item = null;
		Position itemPos;
		while ((!toVisit1.isEmpty() || !toVisit5.isEmpty() || !toVisit10.isEmpty()) && !found) {
			if(toVisit1.isEmpty()) {
				if(toVisit5.isEmpty()) {
					item = toVisit10.remove(toVisit10.size()-1);
				} else {
					item = toVisit5.remove(toVisit5.size()-1);
				}
			} else {
				item = toVisit1.remove(toVisit1.size()-1);
			}
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
						switch(succ.getCost()) {
						case 1:
							toVisit1.add(0,succ);
							break;
						case 5:
							toVisit5.add(0,succ);
							break;
						case 10:
							toVisit10.add(0,succ);
							break;
						}
					}
				}
			}
		}
		return paths.get(item);
	}
	
	public ArrayList<Position> makePathUCS() {
		PriorityQueue<PositionWithCost> toVisit = new PriorityQueue<PositionWithCost>(new PositionComparator());
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
		int x = pos.getPosX();
		int y = pos.getPosY();
		if (x > 0) {
			int newX = x-1;
			int newCost = map[newX][y];
			if (!finalPos.equals(newX, y))
				succs.add(new PositionWithCost(newX, y, newCost));
		}
		if (x < map.length-1) {
			int newX = x+1;
			int newCost = map[newX][y];
			if (!finalPos.equals(newX, y))
				succs.add(new PositionWithCost(newX, y, newCost));
		}
		if (y > 0) {
			int newY = y-1;
			int newCost = map[x][newY];
			if (!finalPos.equals(x, newY))
				succs.add(new PositionWithCost(x, newY, newCost));
		}
		if  (y < map[0].length-1) {
			int newY = y+1;
			int newCost = map[x][newY];
			if (!finalPos.equals(x, newY))
				succs.add(new PositionWithCost(x, newY, newCost));
		}
		return succs;
	}
}
