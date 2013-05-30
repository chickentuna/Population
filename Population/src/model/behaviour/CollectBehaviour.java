package model.behaviour;

import kernel.Progress;
import kernel.managers.RessourceManager;
import model.VState;
import model.Villager;
import model.nature.Produce;

public final class CollectBehaviour extends Behaviour {

	public final static int DURATION = 1;
	public Produce collecting = null;
	protected Progress progress = null;
	
	public CollectBehaviour(Produce collecting) {
		super();
		this.collecting = collecting;
	}

	@Override
	protected void execution(Villager owner) {
		if (progress.getPercentage() == 100) {
			RessourceManager.get().add(collecting);
			owner.setCollecting(null);
			owner.abandonBehaviour(this);
			//TODO: consider automating abandonment process: if deactivated and NOT waiting, owner.abandon();
			owner.setState(VState.IDLE);
			deactivate();
		}
	}

	@Override
	public void onAdopt(Villager owner) {
		progress = new Progress(DURATION);
		owner.setState(VState.COLLECTING);
		owner.setCollecting(collecting);
	}
}
