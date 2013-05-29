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
				waitingFor = null;
				activate();
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
	
	public void deactivate() {
		active=false;
	}
	
	public void activate() {
		active=true;
	}
	
}
