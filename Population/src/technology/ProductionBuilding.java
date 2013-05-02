package technology;

import model.Discoverable;
import model.nature.Land;
import model.nature.Produce;

public enum ProductionBuilding implements Building, Discoverable {

	WHEATFARM(Land.PLAIN, Produce.WHEAT) {
		public String toString() {
			return name();
		}
	};

	private Land land;
	private Produce produce;

	private ProductionBuilding(Land land, Produce produce) {
		this.land = land;
		this.produce = produce;
	}

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
}
