package model;


public interface Behaviour {
	
	public void execute(Villager owner);

	public static final Behaviour STANDARD = new Behaviour() {
		public void execute(Villager owner) {
			if (owner.state == State.IDLE) {
				double percent = Math.random() * 100;
				if (percent < 4) {
					owner.state = State.WANDERING;
					owner.direction = (float) Math.random() * 360;
				}
			} else if (owner.state == State.WANDERING) {
				double percent = Math.random() * 100;
				if (percent < 5) {
					owner.state = State.IDLE;
				}
				owner.step_foward();
			}
		}
	};
	
}
