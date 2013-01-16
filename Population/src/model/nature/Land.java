package model.nature;

import model.Discoverable;

public enum Land implements Discoverable {
	BEACH(Produce.CRAB),
	PLAIN(),
	SEA(),
	LAKE(),
	WOOD(Produce.LUMBER),
	HILL(Produce.STONE);
	
	Produce[] produce;
	
	Land(Produce... produce) {
		this.produce = produce;
	}

	public String getIdentifier() {
		return this.name();
	}
	
}
