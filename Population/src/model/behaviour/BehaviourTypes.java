package model.behaviour;

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
import model.Discoverable;
import model.Producer;
import model.VState;
import model.Villager;
import model.technology.BType;
import model.technology.Building;

public enum BehaviourTypes {

	STANDARD {

		@Override
		public Behaviour getBase() {
			return new Behaviour() {

				@Override
				public void onAdopt(Villager owner) {
					owner.setState(VState.IDLE);
				}

				@Override
				public void execute(Villager owner) {

					// Wander around
					if (owner.getState() == VState.IDLE) {
						double percent = Math.random() * 100;
						if (percent < 4) {
							owner.setState(VState.WANDERING);
							owner.setDirection((float) Math.random() * 360);
						}
					} else if (owner.getState() == VState.WANDERING) {
						double percent = Math.random() * 100;
						if (percent < 5) {
							owner.setState(VState.IDLE);
						}
						owner.step_foward();
					}

					// Activity
					if (Chance.onceEveryXSeconds(20)) {
						Behaviour todo = DecisionManager.get().somethingUseful(
								owner);
						owner.abandonBehaviour(this);
						owner.adoptBehaviour(todo);
					}
				}
			};
		}
	};

	
	
	/*
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

			if (owner.intention == this) {
				owner.setProgressFor(this, DURATION);
				owner.state = VState.LABOURING;
				owner.intention = null;
			} else {
				Point better = getBetterSolution(owner);
				owner.abandonBehaviour(this);
				owner.goingTo = better;
				owner.intention = this;
				owner.adoptBehaviour(GOING);
				// TODO: onAdopt everybody, THEN move them around ?
			}
		}

		private Point getBetterSolution(Villager v) {
			// Get a list of points plus their score.
			List<Decision> places = WorldManager.get()
					.getProductionDecisionsAround(v, 2);
			return (Point) Chance.pickFrom(places).getParam();
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
		public void execute(Villager owner) {
			owner.step_towards(owner.goingTo);
			if (WorldManager.get().onSameTile(owner, owner.goingTo)) {
				owner.abandonBehaviour(this);
				owner.adoptBehaviour(owner.intention);
				owner.goingTo = null;
			}
		}

	};*/

	public abstract Behaviour getBase();

}
