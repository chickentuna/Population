package pop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class ProductionBuilding extends Building {
	
	protected Map<Good,Integer> stock;
	protected List<Position> workforce;
	protected List<Production> production;
	
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
	
	
	public ProductionBuilding() {
		workforce = new LinkedList<Position>();
		stock = new HashMap<Good,Integer>();
		stock.put(Good.NONE, 0);
		production = new LinkedList<Production>();
		initializeWorkforce();
	}
	
	public boolean isFull() {
		for (Position pos : workforce) {
			if (!pos.isReady()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean canStartProduction() {
		return isFull() && hasInput() && workersPresent();
	}
	
	private boolean workersPresent() {
		return true;
	}

	public boolean hasInput() {
		for (Production prod : production) {
			Good input = prod.getInput();
			if (input !=null && !(stock.containsKey(input) && stock.get(input)>=prod.getInputAmount())) {
				return false;
			}
		}
		return true;
	}

	public abstract void initializeWorkforce();
	
	public List<Production> getProduction() {
		return production;
	}
	public List<Position> getWorkforce() {
		return workforce;
	}

	public ProductionBuilding setProduction(List<Production> production) {
		this.production = production;
		return this;
	}
	
}
