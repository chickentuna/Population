package model;

import java.util.Iterator;
import java.util.LinkedList;

import kernel.DiscoveryManager;

import model.nature.Land;

import static model.VState.*;

public interface Behaviour {
	
	public void execute(Villager owner);

	public static final Behaviour STANDARD = new Behaviour() {
		public void execute(Villager owner) {
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
		}
	};
	
	public static final Behaviour CURIOSITY = new Behaviour() {
		public void execute(Villager owner) {
			LinkedList<Discoverable> surroundings  = owner.getSurroundings();
			Iterator<Discoverable> it = surroundings.iterator();
			while (it.hasNext()) {
				DiscoveryManager.addDiscovery(it.next());
			}
		}
	};
	
	public static final Behaviour LUMBERJACK = new Behaviour() {
		public void execute(Villager owner) {
			
		}
	};
	
	
	
}
