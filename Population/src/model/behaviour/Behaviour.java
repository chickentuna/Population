package model.behaviour;

import model.Villager;

public abstract class Behaviour {
	protected boolean active = false;
	protected Behaviour waitingFor = null;
	
	protected abstract void execution(Villager owner);
	
	public void execute(Villager owner) {
		if (isActive())
			execution(owner);
	}
	
	public void onAdopt(Villager owner) {		
	}

	public void onAbandon(Villager owner) {		
	}
	
	public boolean isActive() {
		return active;
	}
}
