package kernel.managers;

import java.util.LinkedList;
import java.util.List;

import kernel.Chance;
import kernel.Decision;
import model.Villager;
import model.behaviour.Behaviour;
import model.behaviour.LabourBehaviour;
import model.nature.Land;
import model.technology.Building;

//TODO: Any part of this class that can be modified by the villager, should belong to Villager class.

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
		List<Decision> poss = UsefulPossibilitiesFor(v);
		if (poss == null)
			return null;

		Decision choice = Chance.pickFrom(poss);
		return (Behaviour) (choice.getParam());
	}

	private List<Decision> UsefulPossibilitiesFor(Villager v) {
		LinkedList<Decision> decisions = new LinkedList<>();
		// TODO: incorporate what to build here (if anything can be built,


		decisions.add(new Decision() {

			@Override
			public int getWeight() {
				return 5;
			}

			@Override
			public Object getParam() {
				return new LabourBehaviour();
			}
		});

		return decisions;
	}

	public Building somethingToBuild(Villager v) {
		List<Decision> poss = getBuildingPossibilities(v);
		if (poss.size() == 0)
			return null;

		Decision choice = Chance.pickFrom(poss);
		return (Building) (choice.getParam());
	}

	private List<Decision> getBuildingPossibilities(Villager v) {
		List<Decision> poss = new LinkedList<Decision>();
		Land on = WorldManager.get().getLandUnder(v);

		int food = RessourceManager.get().getFood();
		int population = RessourceManager.get().getPopulation();

		return poss;
	}
}
