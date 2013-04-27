package kernel.managers;

import java.util.LinkedList;
import java.util.List;

import kernel.BehaviourDecision;
import kernel.Chance;
import kernel.Decision;
import model.Behaviour;
import model.Villager;
import model.nature.Land;
import technology.Building;

public class DecisionManager {

	private static DecisionManager self = null;

	public static DecisionManager get() {
		if (self == null) {
			self = new DecisionManager();
		}
		return self;
	}

	private DecisionManager() {

	}

	public Behaviour somethingUseful(Villager v) {
		List<Decision> possibilities = UsefulPossibilitiesFor(v);
		if (possibilities == null)
			return Behaviour.STANDARD;

		Decision choice = Chance.pickFrom(possibilities);
		return ((BehaviourDecision) choice).getBehaviour();
	}

	private List<Decision> UsefulPossibilitiesFor(Villager v) {
		// TODO : "Parse" possibilities out of a vilagers situation and
		// available technologies + ressources.

		LinkedList<Decision> decisions = new LinkedList<>();
		Land on = WorldManager.get().getLandUnder(v);
		Building in = WorldManager.get().getBuildingUnder(v);

		if (in == null) {

		}

		return null;
	}
}
