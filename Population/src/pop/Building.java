package pop;

import java.util.Map;

public abstract class Building extends Entity {
	
	protected Type type;
	protected Land requiredLand;
	protected Map<Good, Integer> materials;
	
	public enum Type { HOME, FARM, MINE }
	
	public int getX() {
		return x;
	}

	public Land getLand() {
		return requiredLand;
	}
	
	public Building setLand(Land land) {
		requiredLand = land;
		return this;
	}
	
	public int getY() {
		return y;
	}

	public Building setX(int x) {
		this.x = x;
		return this;
	}

	public Building setY(int y) {
		this.y = y;
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
