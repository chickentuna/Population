package pop;

import java.util.LinkedList;
import java.util.List;

public class Position {
	
	List<Villager> workers;
	Job job;
	private int amount;

	public Position(Building owner, Job.Type type, int amount) {
		this.job = new Job(type).setWorkplace(owner);
		workers = new LinkedList<Villager>();
	}
	
	public boolean isReady() {
		for (Villager v : workers) {
			if (v.getJob() != job) {
				return false;
			}
		}
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
	
}

