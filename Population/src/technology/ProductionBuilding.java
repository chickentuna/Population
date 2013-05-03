package technology;

import static model.nature.Land.BEACH;
import static model.nature.Land.HILL;
import static model.nature.Land.PLAIN;
import static model.nature.Land.WOOD;
import static model.nature.Produce.FISH;
import static model.nature.Produce.ORE;
import static model.nature.Produce.PLANKS;
import static model.nature.Produce.WHEAT;
import model.Discoverable;
import model.nature.Land;
import model.nature.Produce;

//@formatter:off
public enum ProductionBuilding implements Building, Discoverable {

	WHEATFARM(PLAIN, WHEAT),
	MINE(HILL, ORE),
	FISHERY(BEACH, FISH),
	LUMBERMILL(WOOD, PLANKS);

	private Land land;
	private Produce produce;

	private ProductionBuilding(Land land, Produce produce) {
		this.land = land;
		this.produce = produce;
	}

	@Override
	public Land getLand() {
		return land;
	}

	public Produce getProduce() {
		return produce;
	}

	@Override
	public String getIdentifier() {
		return this.name();
	}

	@Override
	public BType getType() {
		return BType.PRODUCTION;
	}
}
