package technology;

import model.nature.Land;

//@formatter:off
public enum RBMaterial {
	STRAW(Land.PLAIN),
	WOOD(Land.WOOD),
	STONE(Land.HILL),
	SAND(Land.BEACH);
	
	private Land land;
	
	RBMaterial(Land land) {
		this.land = land;
	}
	
	public Land getLand() {
		return land;
	}
}
