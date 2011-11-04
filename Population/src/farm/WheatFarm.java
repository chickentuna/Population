package farm;

import pop.Good;
import pop.Job;
import pop.Production;

public class WheatFarm extends Farm {

	public static final int FARMERS = 4;
	
	public WheatFarm() {
		super();
		production.add(new Production(Good.WHEAT,10));
	}

	public void initializeWorkforce() {
		workforce.add(new Position(this,Job.Type.FARMER, FARMERS));	
	}

}
