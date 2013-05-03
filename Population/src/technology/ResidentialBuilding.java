package technology;

import static model.nature.Land.PLAIN;
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
	
	WOODEN_HUT(Land.WOOD), 
	WOODEN_HOUSE(Land.WOOD),
	
	STONE_HUT(Land.HILL), 
	STONE_HOUSE(Land.HILL),
	
	SAND_HUT(Land.BEACH), 
	SAND_HOUSE(Land.BEACH),
	
	BRICK_HOUSE(null),
	MANSION(null);

	private Land land;

	ResidentialBuilding(Land land) {
		this.land = land;
	}

	@Override
	public String getIdentifier() {
		return name();
	}

	@Override
	public Land getLand() {
		return land;
	}

	@Override
	public BType getType() {
		return BType.RESIDENTIAL;
	}
}
