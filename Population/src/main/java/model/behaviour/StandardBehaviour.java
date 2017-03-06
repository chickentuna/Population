package model.behaviour;

import kernel.Chance;
import kernel.managers.DecisionManager;
import model.VState;
import model.Villager;

public final class StandardBehaviour extends Behaviour {

	@Override
	public void onAdopt(Villager owner) {
		owner.setState(VState.IDLE);
	}

	@Override
	protected void execution(Villager owner) {

		// Wander around
		if (owner.getState() == VState.IDLE) {
			double percent = Math.random() * 100;
			if (percent < 4) {
				owner.setState(VState.WANDERING);
				owner.setDirection((float) (Math.random() * 2 * Math.PI));

			}
		} else if (owner.getState() == VState.WANDERING) {
			double percent = Math.random() * 100;
			if (percent < 5) {
				owner.setState(VState.IDLE);
			}
			owner.step_foward();
		}

		// Activity
		if (Chance.onceEveryXSeconds(20)) {
			Behaviour todo = DecisionManager.get().somethingUseful(owner);
			if (todo != null) {
				deactivate();
				waitingFor = todo;
				owner.adoptBehaviour(todo);
			}
		}
	}

}
