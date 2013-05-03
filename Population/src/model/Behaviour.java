package model;

import java.util.Iterator;
import java.util.LinkedList;

import kernel.Chance;
import kernel.managers.DecisionManager;
import kernel.managers.DiscoveryManager;
import kernel.managers.RessourceManager;
import kernel.managers.WorldManager;
import technology.BType;
import technology.Building;

public enum Behaviour {

	STANDARD {
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
			if (Chance.onceEveryXMinutes(2)) {
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

		// TODO: labour should be different for each type of poduce.
		// -> Get produce before end. and set vars for it.

		@Override
		public void execute(Villager owner) {
			if (owner.getProgressFor(this) == null) {
				owner.setProgressFor(this, DURATION);
			}
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
	},

	COLLECT {
		public final static int DURATION = 1;

		@Override
		public void execute(Villager owner) {

			if (owner.collecting == null) {
				owner.adoptBehaviour(STANDARD);
				owner.abandonBehaviour(this);
			}
			if (owner.getProgressFor(this) == null) {
				owner.setProgressFor(this, DURATION);
			}
			if (owner.getProgressFor(this).getPercentage() == 100) {
				owner.clearProgressFor(this);
				RessourceManager.get().add(owner.collecting);
				owner.collecting = null;
			}
		}

	},

	BUILD {
		@Override
		public void execute(Villager owner) {
			if (owner.building == null) {
				owner.setBuilding(DecisionManager.get().somethingToBuild(owner));
			}// TODO: build.
		}
	};

	public void onAdopt() {
	}

	public void onAbandon() {
	}

	public abstract void execute(Villager owner);

}
