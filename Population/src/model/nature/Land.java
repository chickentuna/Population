package model.nature;

import static model.nature.Produce.APPLE;
import static model.nature.Produce.CRAB;
import static model.nature.Produce.LOG;
import static model.nature.Produce.STONE;
import model.Discoverable;

//@formatter:off
public enum Land implements Discoverable {
	BEACH(CRAB),
	PLAIN(APPLE),
	SEA(),
	LAKE(),
	WOOD(LOG),
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
