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

public final class LabourBehaviour extends Behaviour {

	// TODO: labour duration should be different for each type of
	// poduce.
	public static final int DURATION = 4;
	private static final int GOTO_BETTER = 0;
	private static final int WORK = 1;
	private static final int GATHER = 2;
	private static final int GIVE_UP = 2;

	protected Produce collecting = null;
	protected Progress progress = null;
	private int state;

	@Override
	protected void execution(Villager owner) {
		if (state==GOTO_BETTER) {
			progress = new Progress(DURATION);
			owner.setState(VState.LABOURING);
			state = WORK;
		}
		
		if (state == WORK && progress.getPercentage() == 100) {
			progress = null;
			Producer producer;
			producer = WorldManager.get().getLandUnder(owner);
			
			collecting = producer.getOneProduce();
			if (collecting == null) {
				//throw new RuntimeException("No produce found");
				waitingFor = null;
				state = GATHER;
				owner.setState(VState.IDLE);
				deactivate();
			} else {
				
				Behaviour intention = new CollectBehaviour(collecting);
				state = GATHER;
				owner.adoptBehaviour(intention);
				waitingFor = intention;
				deactivate();
			}
		} else if (state == GATHER) {
			deactivate();
			owner.abandonBehaviour(this);
		}
		
		
	}

	@Override
	public void onAdopt(Villager owner) {
		Point better = getBetterSolution(owner);
		if (better == null) {
			state = GIVE_UP;
			return;
		}
		deactivate();
		waitingFor = new GoingBehaviour(better);
		owner.adoptBehaviour(waitingFor);
		state = GOTO_BETTER;
	}

	private Point getBetterSolution(Villager owner) { 
		//TODO: Some villagers have a higher chance to make a wrong decision.
		List<Decision> places = WorldManager.get().getProductionDecisionsAround(owner, 2);
		if (places.size()==0) {
			return null;
		}
		return (Point) Chance.pickFrom(places).getParam();
	}
	
}
