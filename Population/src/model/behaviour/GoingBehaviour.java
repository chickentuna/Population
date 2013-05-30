package model.behaviour;

import kernel.Point;
import kernel.managers.WorldManager;
import model.Villager;

public class GoingBehaviour extends Behaviour {

	public Point goingTo = null;
	protected Behaviour intention = null;

	public GoingBehaviour(Point loc) {
		super();
		goingTo = loc;
	}

	@Override
	protected void execution(Villager owner) {
		owner.step_towards(goingTo);
		if (WorldManager.get().onSameTile(owner, goingTo)) {
			owner.abandonBehaviour(this);
			goingTo = null;
		}
	}

}
