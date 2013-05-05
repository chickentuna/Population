package kernel.events;

import model.Villager;

public class VillagerEvent {

	public static final int BIRTH = 0;
	public static final int DEATH = 1;
	public static final int MOVE = 2;// TODO: Move and Labour useless ?
	public static final int LABOUR = 3;

	int type;
	Villager villager;

	public VillagerEvent(int type, Villager villager) {
		this.type = type;
		this.villager = villager;
	}

	public Villager getVillager() {
		return villager;
	}

	public int getType() {
		return type;
	}
}
