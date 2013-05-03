package technology;

import model.Discoverable;
import model.nature.Land;

/**
 * TODO: instead of each type of house with land + capacity, have a structure
 * closer to : land -> house material housetype -> capacity
 */

//@formatter:off

public enum ResidentialBuilding implements Building, Discoverable {
	HUT(2,true),
	HOUSE(4,true),
	BRICK_HOUSE(10, false),
	MANSION(50, false);
	
	private int capacity;
	private boolean abstrac;
	
	public int getCapacity() {
		return capacity;
	}

	public boolean isAbstract() {
		return abstrac;
	}

	ResidentialBuilding(int capacity, boolean abstrac) {
		this.capacity = capacity;
		this.abstrac = abstrac;
	}
	
	@Override
	public String getIdentifier() {
		return name();
	}

	@Override
	public Land getLand() {
		return null;
	}

	@Override
	public BType getType() {
		return BType.RESIDENTIAL;
	}
}
