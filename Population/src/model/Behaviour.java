package model;

import static model.VState.IDLE;
import static model.VState.WANDERING;

import java.util.Iterator;
import java.util.LinkedList;

import kernel.Chance;
import kernel.Progress;
import kernel.managers.DecisionManager;
import kernel.managers.DiscoveryManager;
import kernel.managers.WorldManager;
import technology.BType;
import technology.Building;

public interface Behaviour {

	public void execute(Villager owner);

	public static final Behaviour STANDARD = new Behaviour() {
		public void execute(Villager owner) {
			// Wander around
			if (owner.state == IDLE) {
				double percent = Math.random() * 100;
				if (percent < 4) {
					owner.state = WANDERING;
					owner.direction = (float) Math.random() * 360;
				}
			} else if (owner.state == WANDERING) {
				double percent = Math.random() * 100;
				if (percent < 5) {
					owner.state = IDLE;
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
	};

	// TODO: scrap curiosity and have discoveries happen in an event like
	// fashion.
	public static final Behaviour CURIOSITY = new Behaviour() {
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
	};

	public static final Behaviour LABOUR = new Behaviour() {

		@Override
		public void execute(Villager owner) {
			if (owner.progress == null) {
				owner.progress = new Progress(4);
			}
			if (owner.progress.getPercentage() == 100) {
				owner.progress = null;
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
	};

	public static final Behaviour COLLECT = new Behaviour() {

		@Override
		public void execute(Villager owner) {
			// TODO Auto-generated method stub

		}

	};

	public static final Behaviour BUILD = new Behaviour() {

		@Override
		public void execute(Villager owner) {
			if (owner.building == null) {
				owner.setBuilding(DecisionManager.get().somethingToBuild(owner));
			}

		}
	};

}
