package achievement4;

public class Position {

	private int posX, posY;

	public Position(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
	}

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
	
	public boolean equals(PositionWithCost pwc) {
		return pwc.getPosX() == this.getPosX() && pwc.getPosY() == this.getPosY();
	}
	
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
