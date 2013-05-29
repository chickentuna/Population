package model.behaviour;

import model.Villager;

public abstract class Behaviour {
	protected boolean active = true;
	protected Behaviour waitingFor = null;
	
	protected abstract void execution(Villager owner);
	
	public void execute(Villager owner) {
		if (isActive()) {
			execution(owner);
		} else if (waitingFor!=null) {
			if (!waitingFor.isActive()) {
				active = true;
			}
		}
	}
	
	public void onAdopt(Villager owner) {		
	}

	public void onAbandon(Villager owner) {		
	}
	
	public boolean isActive() {
		return active;
	}
}
