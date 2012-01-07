package pop;

public class Lumbermill extends ProductionBuilding {

	public Lumbermill(float x, float y) {
		super(x, y);
		height=1;
		width=1;
		tiles = new int[1][1];
		tiles[0][0]=Tiles.LUMBERMILL;
	}

	public Lumbermill() {
		this(0, 0);
	}

	@Override
	public void initializeWorkforce() {
		// TODO Auto-generated method stub

	}

}
