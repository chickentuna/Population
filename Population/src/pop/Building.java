package pop;

import java.util.Map;

import mapping.Land;

public abstract class Building extends Entity {
	
	//protected Type type;
	protected Land requiredLand;
	protected Map<Good, Integer> materials;
	protected float constructionTime = 0f;
	protected float constructionProgress = 0f;  
	
	//public enum Type { HOME, FARM, MINE, LUMBERMILL }
	
	public Building(float x, float y) {
		super(x,y);
	}

	public Land getLand() {
		return requiredLand;
	}
	
	public Building setLand(Land land) {
		requiredLand = land;
		return this;
	}

/*	public Building setType(Type type) {
		this.type = type;
		return this;
	}

	public Type getType() {
		return type;
	}
*/

}
