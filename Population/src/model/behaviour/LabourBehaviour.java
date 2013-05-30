package model.behaviour;

import java.util.List;

import kernel.Chance;
import kernel.Decision;
import kernel.Point;
import kernel.Progress;
import kernel.managers.WorldManager;
import model.Producer;
import model.VState;
import model.Villager;
import model.nature.Produce;
import model.technology.BType;
import model.technology.Building;

public class LabourBehaviour extends Behaviour {

	// TODO: labour duration should be different for each type of
	// poduce.
	public static final int DURATION = 4;

	protected Produce collecting = null;
	protected Progress progress = null;
	private Villager owner;

	@Override
	protected void execution(Villager owner) {
		if (progress.getPercentage() == 100) {
			progress = null;
			Producer producer;
			Building in = WorldManager.get().getBuildingUnder(owner);
			if (in != null && in.getType() == BType.PRODUCTION) {
				producer = (Producer) in;
			} else {
				producer = WorldManager.get().getLandUnder(owner);
			}

			collecting = producer.getProduce();
			owner.abandonBehaviour(this);
			Behaviour intention = new CollectBehaviour(collecting);
			owner.adoptBehaviour(intention);
		}
	}

	@Override
	public void onAdopt(Villager owner) {
		this.owner= owner; 
		Point better = getBetterSolution();
		deactivate();
		waitingFor = new GoingBehaviour(better);
		owner.adoptBehaviour(waitingFor);
	}
	
	@Override
	public void activate() {
		super.activate();
		progress = new Progress(DURATION);
		owner.setState(VState.LABOURING);
		owner = null;
	}

	private Point getBetterSolution() {
		// Get a list of points plus their score.
		List<Decision> places = WorldManager.get().getProductionDecisionsAround(owner, 2);
		return (Point) Chance.pickFrom(places).getParam();
	}

}
