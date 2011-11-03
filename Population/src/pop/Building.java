package pop;

public abstract class Building extends Entity {
	
	protected int radius;
	protected Type type;
	
	public enum Type { HOME, FARM, MINE }
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadius() {
		return radius;
	}

	public Building setX(int x) {
		this.x = x;
		return this;
	}

	public Building setY(int y) {
		this.y = y;
		return this;
	}

	public Building setRadius(int radius) {
		this.radius = radius;
		return this;
	}

	public Building setType(Type type) {
		this.type = type;
		return this;
	}

	public Type getType() {
		return type;
	}
	
	
}
