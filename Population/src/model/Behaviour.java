package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import kernel.Chance;
import kernel.Decision;
import kernel.Point;
import kernel.managers.DecisionManager;
import kernel.managers.DiscoveryManager;
import kernel.managers.RessourceManager;
import kernel.managers.WorldManager;
import model.technology.BType;
import model.technology.Building;

public enum Behaviour {

	STANDARD {
		
		@Override
		public void onAdopt(Villager owner) {
			owner.state = VState.IDLE;
		}
		
		@Override
		public void execute(Villager owner) {
			
			// Wander around
			if (owner.state == VState.IDLE) {
				double percent = Math.random() * 100;
				if (percent < 4) {
					owner.state = VState.WANDERING;
					owner.direction = (float) Math.random() * 360;
				}
			} else if (owner.state == VState.WANDERING) {
				double percent = Math.random() * 100;
				if (percent < 5) {
					owner.state = VState.IDLE;
				}
				owner.step_foward();
			}

			// Activity
			if (Chance.onceEveryXSeconds(20)) {
				Behaviour todo = DecisionManager.get().somethingUseful(owner);
				owner.abandonBehaviour(this);
				owner.adoptBehaviour(todo);
			}
		}
	},

	// TODO: scrap curiosity and have discoveries happen in an event like
	// fashion.
	CURIOSITY {
		public void execute(Villager owner) {
			LinkedList<Discoverable> surroundings = getDiscoverablesAround(owner);
			Iterator<Discoverable> it = surroundings.iterator();
			while (it.hasNext()) {
				DiscoveryManager.get().addDiscovery(it.next());
			}
		}

		// TODO: have discoveries unlock ONLY when you arrive on new land.
		public LinkedList<Discoverable> getDiscoverablesAround(Villager owner) {
			LinkedList<Discoverable> discoverables = new LinkedList<>();
			discoverables.add(WorldManager.get().getLandUnder(owner));

			return discoverables;
		}
	},

	LABOUR {
		public static final int DURATION = 4;
		// TODO: labour duration should be different for each type of poduce.
		// -> Get produce before end. and set vars for it.

		//TODO: Have villager check surroundings for a better labour opportuniy before labouring here 
		// (small chance of staying)
		@Override
		public void execute(Villager owner) {
			if (owner.getProgressFor(this).getPercentage() == 100) {
				owner.clearProgressFor(this);
				Producer producer;
				Building in = WorldManager.get().getBuildingUnder(owner);
				if (in != null && in.getType() == BType.PRODUCTION) {
					producer = (Producer) in;
				} else {
					producer = WorldManager.get().getLandUnder(owner);
				}

				owner.collecting = producer.getProduce();
				owner.abandonBehaviour(this);
				owner.adoptBehaviour(COLLECT);
			}
		}

		@Override
		public void onAdopt(Villager owner) {
			Point better = getBetterSolution(owner);
			if (better == null) {			
			owner.setProgressFor(this, DURATION);
			owner.state = VState.LABOURING;
			} else {
				owner.abandonBehaviour(this);
				owner.goingTo = better;
				owner.intention = this;
				owner.adoptBehaviour(GOING);
			}
		}

		private Point getBetterSolution(Villager v) {
			//Get a list of points plus their score.
			List<Decision> places = WorldManager.get().getProductionDecisionsAround(v, 2);
			return (Point)Chance.pickFrom(places).getParam();
		}
	},

	COLLECT {
		public final static int DURATION = 1;

		@Override
		public void execute(Villager owner) {
			if (owner.getProgressFor(this).getPercentage() == 100) {
				owner.clearProgressFor(this);
				RessourceManager.get().add(owner.collecting);
				owner.collecting = null;
				owner.adoptBehaviour(STANDARD);
				owner.abandonBehaviour(this);
			}
		}

		@Override
		public void onAdopt(Villager owner) {
			owner.setProgressFor(this, DURATION);
			owner.state = VState.COLLECTING;
		}

	},

	BUILD {
		@Override
		public void execute(Villager owner) {
			if (owner.building == null) {
				owner.setBuilding(DecisionManager.get().somethingToBuild(owner));
			}// TODO: build.
		}
	}, 
	
	GOING {

		@Override
		public void onAdopt(Villager owner) {
			owner.direction = new Point(owner.getX(), owner.getY()).directionTo(owner.goingTo);
		}
		
		@Override
		public void execute(Villager owner) {
			owner.step_foward();
			if (/*TODO: Villager has arrived at destination*/true) {
				owner.abandonBehaviour(this);
				owner.adoptBehaviour(owner.intention);
				owner.intention = null;
				owner.goingTo = null;
			}
		}
		
	};

	public void onAdopt(Villager owner) {
	}

	public void onAbandon(Villager owner) {
	}

	public abstract void execute(Villager owner);

}
