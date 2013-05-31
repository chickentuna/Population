package model.technology;

import static model.nature.Land.Type.*;
import model.Discoverable;
import model.nature.Land;

/**
 * TODO: instead of each type of house with land + capacity, have a structure
 * closer to : land -> house material housetype -> capacity
 */

//@formatter:off

public enum ResidentialBuilding implements Building, Discoverable {
	STRAW_HUT(PLAIN), 
	STRAW_HOUSE(PLAIN),
	
	WOODEN_HUT(WOOD), 
	WOODEN_HOUSE(WOOD),
	
	STONE_HUT(HILL), 
	STONE_HOUSE(HILL),
	
	SAND_HUT(BEACH), 
	SAND_HOUSE(BEACH),
	
	BRICK_HOUSE(null),
	MANSION(null);

	private Land.Type land;

	ResidentialBuilding(Land.Type land) {
		this.land = land;
	}

	@Override
	public String getIdentifier() {
		return name();
	}

	@Override
	public Land.Type getLand() {
		return land;
	}

	@Override
	public BType getType() {
		return BType.RESIDENTIAL;
	}
}
