package model.nature;

import static model.nature.Produce.APPLE;
import static model.nature.Produce.CRAB;
import static model.nature.Produce.LOG;
import static model.nature.Produce.STONE;
import model.Discoverable;
import model.Producer;

//TODO: Lands as classes

//@formatter:off
public class Land extends Producer implements Discoverable {
	public enum Type {
		BEACH(CRAB),
		PLAIN(APPLE),
		SEA(),
		LAKE(),
		WOOD(LOG),
		HILL(STONE);
	
		Produce[] produce;

		Type(Produce... produce) {
			this.produce = produce;
		}

	}
	
	private Type type;

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

}
