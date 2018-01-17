package achievement4;

public class Position {

	private int posX, posY;

	/**
	 * Structure utilisee pour les stockages de positions ne requerant pas de cout
	 * @param posX la position en x
	 * @param posY la positino en y
	 */
	public Position(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

	/**
	 * Constructeur de copie utilisee notamment pour les positions deja visitees
	 * @param pwc
	 */
	public Position(PositionWithCost pwc) {
		this.posX = pwc.getPosX();
		this.posY = pwc.getPosY();
	}
	
	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}
	
	/**
	 * Comparaison entre la position et une instance de PositionWithCost
	 * @param pwc
	 * @return vrai si les positions sont egales en x et en y, faux sinon
	 */
	public boolean equals(PositionWithCost pwc) {
		return pwc.getPosX() == this.getPosX() && pwc.getPosY() == this.getPosY();
	}
	
	/**
	 * Comparaison entre la position et des coordonnees x et y
	 * @param x
	 * @param y
	 * @return vrai si les positions sont egales en x et en y,faux sinon
	 */
	public boolean equals(int x, int y) {
		return this.getPosX() == x && this.getPosY() == y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + posX;
		result = prime * result + posY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (posX != other.posX)
			return false;
		if (posY != other.posY)
			return false;
		return true;
	}
	
	
}
