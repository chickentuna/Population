package model.nature;

public enum Produce {

	APPLE(1, PType.FOOD),
	STONE(1, PType.RESSOURCE),
	CRAB(2, PType.FOOD), 
	LUMBER(2, PType.RESSOURCE);

	private int value;
	private PType type;
	
	Produce(int value, PType type) {
		this.value = value;
		this.type = type;
	}
	
	public int getValue() {
		return value;
	}

	public PType getType() {
		return type;
	}

}
