package pop;

import mapping.Tiles;

public class LumberMill extends ProductionBuilding {

	public LumberMill(float x, float y) {
		super(x, y);
		height=1;
		width=1;
		tiles = new int[1][1];
		tiles[0][0]=Tiles.LUMBERMILL;
		setProduction(new Production(new GoodMap(Good.PLANK,50))
		.setProductionTime(200f)
		.setInput(new GoodMap(Good.LOG,1)));
	}

	public LumberMill() {
		this(0, 0);
	}

	@Override
	public void initializeWorkforce() {
		workforce.add(new Position(this,Job.Type.LUMBERJACK, 5));	

	}

}
