package model.nature;

import static model.nature.Produce.APPLE;
import static model.nature.Produce.CRAB;
import static model.nature.Produce.LOG;
import static model.nature.Produce.STONE;
import model.Discoverable;
import model.Producer;
import model.technology.BType;
import model.technology.Building;
import model.technology.ProductionBuilding;

//TODO: Add properties to Land
public class Land extends Producer implements Discoverable {
	public static enum Type {
		BEACH(CRAB),
		PLAIN(APPLE),
		SEA(),
		LAKE(),
		WOOD(LOG),
		HILL(STONE), NULL;

		Produce[] produce;

		Type(Produce... produce) {
			this.produce = produce;
		}

	}
	
	private Type type;
	private Building building = null;
	
	public Land(Type type) {
		this.type = type;
		this.produce = type.produce.clone();
	}

	@Override
	public String getIdentifier() {
		return type.name();
	}

	public Type getType() {
		return type;
	}
	
	public Building getBuilding() {
		return building;
	}

	public void setBuilding(Building building) {
		this.building = building;
		if (building==null)
			return;
		if (building.getType() == BType.PRODUCTION) {
			produce = ((ProductionBuilding)building).getProduce();
		} else {
			produce = null;
		}
	}
	
	public String toString() {
		return type.name();
	}

}
