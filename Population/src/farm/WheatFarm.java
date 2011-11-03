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

	@Override
	public void initializeWorkforce() {
		workforce.put(Job.Type.FARMER, new Position(4));
		
	}

}
