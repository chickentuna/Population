package model.behaviour;

import kernel.Point;
import kernel.managers.WorldManager;
import model.VState;
import model.Villager;

public class GoingBehaviour extends Behaviour {

	protected Point goingTo = null;
	protected Behaviour intention = null;

	public GoingBehaviour(Point loc) {
		super();
		goingTo = loc;
	}

	@Override
	public void onAdopt(Villager owner) {
		owner.setState(VState.GOING);
	}
	
	@Override
	protected void execution(Villager owner) {
		owner.step_towards(goingTo);
		if (WorldManager.get().onSameTile(owner, goingTo)) {
			owner.abandonBehaviour(this);
			goingTo = null;
		}
	}
	
	public Point getGoingTo() {
		return goingTo;
	}

}
