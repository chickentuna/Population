package model.behaviour;

import kernel.managers.DecisionManager;
import model.Villager;
import model.technology.Building;

public final class BuildBehaviour extends Behaviour {

	protected Building building = null;

	@Override
	protected void execution(Villager owner) {
		if (building == null) {
			building = DecisionManager.get().somethingToBuild(owner);
		}// TODO: build.
	}

}
