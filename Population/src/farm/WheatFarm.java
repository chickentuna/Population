package farm;

import pop.Good;
import pop.GoodMap;
import pop.Job;
import pop.Position;
import pop.Production;

public class WheatFarm extends Farm {

	public static final int FARMERS = 5;
	
	public WheatFarm(float x, float y) {
		super(x,y);
		setProduction(new Production(new GoodMap(Good.WHEAT,50)).setProductionTime(200f));

	}

	public void initializeWorkforce() {
		workforce.add(new Position(this,Job.Type.FARMER, FARMERS));	
	}

}
