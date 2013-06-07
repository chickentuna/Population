package model.technology;

import static model.nature.Land.Type.BEACH;
import static model.nature.Land.Type.HILL;
import static model.nature.Land.Type.PLAIN;
import static model.nature.Land.Type.WOOD;
import static model.nature.Produce.FISH;
import static model.nature.Produce.ORE;
import static model.nature.Produce.PLANKS;
import static model.nature.Produce.WHEAT;
import model.Discoverable;
import model.Producer;
import model.nature.Land;
import model.nature.Produce;

//@formatter:off
public class ProductionBuilding extends Producer implements Building, Discoverable {

	public enum Type {
		WHEATFARM(PLAIN, WHEAT),
		MINE(HILL, ORE),
		FISHERY(BEACH, FISH),
		LUMBERMILL(WOOD, PLANKS);

		private Land.Type land;
		private Produce[] produce;

		private Type(Land.Type land, Produce... produce) {
			this.land = land;
			this.produce = produce;
		}

	}
	
	private Type type;
	
	public ProductionBuilding(Type type) {
		this.type = type;
		produce = type.produce.clone();
	}
	
	@Override
	public BType getType() {
		return BType.PRODUCTION;
	}

	@Override
	public String getIdentifier() {
		return type.name();
	}

	@Override
	public model.nature.Land.Type getLand() {
		return type.land;
	}
}
