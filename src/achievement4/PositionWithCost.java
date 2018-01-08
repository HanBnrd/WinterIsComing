package achievement4;

public class PositionWithCost {

	private int posX, posY;
	private int cost;

	public PositionWithCost(int posX, int posY, int cost) {
		this.posX = posX;
		this.posY = posY;
		this.cost = cost;
	}

	public PositionWithCost(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.cost = 0;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	public int getCost() {
		return cost;
	}
	
}
