package achievement4;

public class PositionWithCost {

	private int posX, posY;
	private int cost;

	/**
	 * Constructeur d'une structeur permettant de stocker le cout avec les coordonnes d'une position
	 * @param posX la position en x
	 * @param posY la position en y
	 * @param cost le cout de cette case
	 */
	public PositionWithCost(int posX, int posY, int cost) {
		this.posX = posX;
		this.posY = posY;
		this.cost = cost;
	}

	/**
	 * Constructeur par defaut, avec un cout de 1
	 * @param posX la position en x
	 * @param posY la position en y
	 */
	public PositionWithCost(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		this.cost = 1;
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
