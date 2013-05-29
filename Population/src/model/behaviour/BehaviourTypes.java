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
import model.nature.Produce;
import model.technology.BType;
import model.technology.Building;
import kernel.Progress;

public enum BehaviourTypes {

	STANDARD {

		@Override
		public Behaviour create() {
			return new Behaviour() {

				@Override
				public void onAdopt(Villager owner) {
					owner.setState(VState.IDLE);
				}

				@Override
				protected void execution(Villager owner) {

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
						Behaviour todo = DecisionManager.get().somethingUseful(owner);
						if (todo !=null) {
							deactivate();
							waitingFor = todo;
							owner.adoptBehaviour(todo);
						}
					}
				}
			};
		}
	},

	// TODO: scrap curiosity and have discoveries happen in an event like
	// fashion.
	CURIOSITY {
		@Override
		public Behaviour create() {
			return new Behaviour() {
				protected void execution(Villager owner) {
					LinkedList<Discoverable> surroundings = getDiscoverablesAround(owner);
					Iterator<Discoverable> it = surroundings.iterator();
					while (it.hasNext()) {
						DiscoveryManager.get().addDiscovery(it.next());
					}
				}

				// TODO: have discoveries unlock ONLY when you arrive on new
				// land.
				public LinkedList<Discoverable> getDiscoverablesAround(
						Villager owner) {
					LinkedList<Discoverable> discoverables = new LinkedList<>();
					discoverables.add(WorldManager.get().getLandUnder(owner));

					return discoverables;
				}
			};
		}
	},

	LABOUR {

		@Override
		public Behaviour create() {
			return new Behaviour() {
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
						Behaviour intention = COLLECT.create();
						intention.setParam("collecting", collecting);
						owner.adoptBehaviour(intention);
					}
				}

				@Override
				public void onAdopt(Villager owner) {
					this.owner= owner; 
					Point better = getBetterSolution();
					deactivate();
					waitingFor = GOING.create();
					waitingFor.setParam("goingTo", better);
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
			};
		}
	},

	COLLECT {
		@Override
		public Behaviour create() {
			return new Behaviour() {
				public final static int DURATION = 1;
				protected Produce collecting = null;
				protected Progress progress = null;
				
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

			};
		}
	},

	BUILD {
		@Override
		public Behaviour create() {
			return new Behaviour() {
				protected Building building = null;

				@Override
				protected void execution(Villager owner) {
					if (building == null) {
						building = DecisionManager.get().somethingToBuild(owner);
					}// TODO: build.
				}
			};
		}
	},

	GOING {
		@Override
		public Behaviour create() {
			return new Behaviour() {
				protected Point goingTo = null;
				protected Behaviour intention = null;

				@Override
				protected void execution(Villager owner) {
					owner.step_towards(goingTo);
					if (WorldManager.get().onSameTile(owner, goingTo)) {
						owner.abandonBehaviour(this);
						owner.adoptBehaviour(intention);
						goingTo = null;
					}
				}

			};
		}
	};

	public abstract Behaviour create();

}
