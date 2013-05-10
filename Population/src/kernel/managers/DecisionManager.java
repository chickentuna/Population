package kernel.managers;

import java.util.LinkedList;
import java.util.List;

import kernel.Chance;
import kernel.Decision;
import model.Villager;
import model.behaviour.Behaviour;
import model.nature.Land;
import model.technology.Building;

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
		/*if (poss == null)
			return Behaviour.STANDARD;*/
		
		Decision choice = Chance.pickFrom(poss);
		return (Behaviour) (choice.getParam());
	}

	private List<Decision> UsefulPossibilitiesFor(Villager v) {
		// TODO : "Parse" possibilities out of a vilagers situation and
		// available technologies + ressources.

		LinkedList<Decision> decisions = new LinkedList<>();
		Building in = WorldManager.get().getBuildingUnder(v);
/*
		if (in == null) {
			decisions.add(new Decision() {

				@Override
				public int getWeight() {
					return 1;
				}

				@Override
				public Object getParam() {
					return Behaviour.BUILD;
				}

			});
		}

		decisions.add(new Decision() {

			@Override
			public int getWeight() {
				return 5;
			}

			@Override
			public Behaviour getParam() {
				return Behaviour.LABOUR;
			}
		});
*/
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
