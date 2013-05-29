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

	public void setParam(String name, Object value) {
		try {
			this.getClass().getField(name).set(this, value);
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
			/*
			System.err.println("In class : "+this.getClass());
			
			Field[] flds = this.getClass().getFields();
			String s = "";
			for (int k=0; k < flds.length; k++) {
				s+=flds[k];
			}
			System.err.println("fields : "+s+". asked for : "+name);
			*/
		}
		
	}
	
	public void deactivate() {
		active=false;
	}
	
	public void activate() {
		active=true;
	}
	
}
