package model.nature;

//static import model.nature.PType;
import static model.nature.PType.FOOD;
import static model.nature.PType.RESSOURCE;

//@formatter:off

public enum Produce {

	APPLE(1, FOOD),
	STONE(1, RESSOURCE),
	CRAB(2, FOOD),
	LUMBER(2, RESSOURCE),
	WHEAT(6, FOOD), 
	FISH(6, FOOD), 
	ORE(6, RESSOURCE), 
	PLANKS(8, RESSOURCE);
	
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
