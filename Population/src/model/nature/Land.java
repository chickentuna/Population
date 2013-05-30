package model.nature;

import static model.nature.Produce.APPLE;
import static model.nature.Produce.CRAB;
import static model.nature.Produce.LOG;
import static model.nature.Produce.STONE;
import model.Discoverable;
import model.Producer;

//TODO: Lands as classes

//@formatter:off
public enum Land implements Discoverable, Producer {
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

	@Override
	public Produce getProduce() {
		if (produce.length == 0)
			return null;
		if (produce.length == 1) {
			return produce[0];
		}
		return produce[(int)(Math.random()*produce.length)]; 
		//TODO: Produce should have a field 'chance' to indicate probability of finding it
		// for lands/buildings that produce multiple things
		
	}

	@Override
	public int getWeight() {
		int weight = 0;
		for (int k = 0; k<produce.length; k++) {
			weight += produce[k].getValue(); //TODO: This code appears twice. (once in ProductionBuilding)
		}
		return weight;
	}

}
