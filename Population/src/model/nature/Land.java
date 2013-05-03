package model.nature;

import static model.nature.Produce.CRAB;
import static model.nature.Produce.LUMBER;
import static model.nature.Produce.STONE;
import model.Discoverable;

//TODO: have decisions manager check if labour is possible for 
// villager before adding it to possiblities

//@formatter:off
public enum Land implements Discoverable {
	BEACH(CRAB),
	PLAIN(),    
	SEA(),
	LAKE(),
	WOOD(LUMBER),
	HILL(STONE);

	Produce[] produce;

	Land(Produce... produce) {
		this.produce = produce;
	}

	public String getIdentifier() {
		return this.name();
	}

	public String toString() {
		return this.name();
	}

}
