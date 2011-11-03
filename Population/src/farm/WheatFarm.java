package farm;

import pop.Good;
import pop.Job;
import pop.Production;

public class WheatFarm extends Farm {

	public WheatFarm() {
		super();
		production.add(new Production()
		.setOutput(Good.WHEAT).setOutputAmount(10)
		);
		
	}

	public void initializeWorkforce() {
		workforce.add(new Position(this,Job.Type.FARMER, 4));
		
	}

}
