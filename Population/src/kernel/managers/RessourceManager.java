package kernel.managers;

import model.Villager;
import model.behaviour.StandardBehaviour;
import model.nature.PType;
import model.nature.Produce;

public class RessourceManager {
	private static final int BASE_FOOD = 20;
	private static final int BASE_RESSOURCE = 20;

	private int food;
	private int ressource;
	private int tech;
	private int population;

	private static RessourceManager self = null;

	public static RessourceManager get() {
		if (self == null) {
			self = new RessourceManager();
		}
		return self;
	}

	public RessourceManager() {
		tech = 0;
		population = 0;
		food = BASE_FOOD;
		ressource = BASE_RESSOURCE;
	}

	public int getFood() {
		return food;
	}
	
	public int getTech() {
		return tech;
	}

	public int getRessource() {
		return ressource;
	}

	public int getPopulation() {
		return population;
	}

	public Villager villagerBirth(int x, int y) {
		population++;
		Villager v = new Villager(x, y);
		v.adoptBehaviour(new StandardBehaviour());
		EntityManager.get().spawn(v);
		return v;
	}
	
	public void villagerDeath(Villager v) {
		population--;
		EntityManager.get().unspawn(v);
	}

	public void add(Produce produce) {
		if (produce.getType() == PType.FOOD) {
			food += produce.getValue();
		} else if (produce.getType() == PType.RESSOURCE) {
			ressource += produce.getValue();
		}
	}

}
