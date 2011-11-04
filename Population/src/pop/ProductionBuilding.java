package pop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class ProductionBuilding extends Building {
	
	protected Map<Good,Integer> stock;
	protected List<Position> workforce;
	protected Production production;
	protected State state;
	protected float timer;
	
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
	
	public enum State { IDLE, ACTIVE }	
	
	public ProductionBuilding() {
		workforce = new LinkedList<Position>();
		stock = new HashMap<Good,Integer>();
		stock.put(Good.NONE, 0);
		initializeWorkforce();
		state = State.IDLE;
	}
	
	public void step() {
		if (state == State.ACTIVE) {
			timer+=1;
			if (timer >= production.getProductionTime()) {
				state = State.IDLE;
				
				
			}
		}
	}
	
	public boolean startProduction() {
		if (can()) {
			for (Position pos : workforce) {
				for (Villager v : pos.workers) {
					v.setState(Villager.State.WORKING);
				}
			}
			state = State.ACTIVE;
			timer = 0;
			return true;
		} 
		else
			return false;
	}
	
	
	public boolean isFull() {
		for (Position pos : workforce) {
			if (!pos.isReady()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean can() {
		return isFull() && hasInput() && workersPresent();
	}
	
	private boolean workersPresent() {
		for (Position pos : workforce) {
			for (Villager v : pos.workers) {
				if ( !v.collidesWith(this) ) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean hasInput() {
		HashMap<Good,Integer> input = production.getInput();
		if (input !=null) {
			for (Good good : input.keySet()) {
				if (!stock.containsKey(good) || (stock.get(good) < input.get(good) ) {
					return false;
				}
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

	public ProductionBuilding setProduction(Production production) {
		this.production = production;
		return this;
	}
	
}
