package farm;

import pop.Good;
import pop.GoodMap;
import pop.Job;
import pop.Position;
import pop.Production;

public class WheatFarm extends Farm {

	public static final int FARMERS = 4;
	
	public WheatFarm() {
		super();
		setProduction(new Production(new GoodMap(Good.WHEAT,10)));
	}

	public void initializeWorkforce() {
		workforce.add(new Position(this,Job.Type.FARMER, FARMERS));	
	}

}
