package pop;

import java.util.LinkedList;
import java.util.List;

public class Position {
	
	private List<Villager> workers;
	private Job job;
	private int amount;

	public Position(Building owner, Job.Type type, int amount) {
		this.job = new Job(type).setWorkplace(owner);
		workers = new LinkedList<Villager>();
		this.amount = amount;
	}
	
	public boolean isFull() {
		return workers.size() == amount;
	}
	
	public int employ(Villager... v) {
		int counter = 0;
		for (Villager candidate : v) {
			if (workers.size()<amount) {
				workers.add(candidate);
				candidate.setJob(job);
				counter++;
			}
		}
		return counter;
	}

	public List<Villager> getWorkers() {
		return workers;
	}
	
}

