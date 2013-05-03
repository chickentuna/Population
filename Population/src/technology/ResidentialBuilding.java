package technology;

import static model.nature.Land.PLAIN;
import static model.nature.Land.ANY;
import model.Discoverable;
import model.nature.Land;

//@formatter:off
public enum ResidentialBuilding implements Building, Discoverable {
	STRAW_HUT(PLAIN), 
	STRAW_HOUSE(PLAIN),
	BRICK_HOUSE(PLAIN),
	MANSION(ANY);

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
}
