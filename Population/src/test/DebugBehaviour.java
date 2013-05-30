package test;

import model.Villager;
import model.behaviour.Behaviour;

public class DebugBehaviour extends Behaviour {

	@Override
	protected void execution(Villager owner) {
		owner.setDirection((float) Math.PI/4);
		owner.step_foward();

	}

}
