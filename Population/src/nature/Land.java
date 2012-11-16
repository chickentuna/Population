package nature;

public enum Land {
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
	
	
	
}
