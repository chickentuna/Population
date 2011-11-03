package pop;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class ProductionBuilding extends Building {
	
	protected Map<Good,Integer> stock;
	protected List<Position> workforce;
	protected List<Production> production;
	
	//In Position? > List of workers (Villagers) to render presence test possible
	// DONE
	public class Position {
		
		List<Villager> list;
		Job.Type type;
		private int amount;
		private Building owner;

		public Position(Building owner, Job.Type type, int amount) {
			this.owner = owner;
			this.amount = amount;
			list = new LinkedList<Villager>();
		}
		
		public boolean isReady() {
			for (Villager v : list) {
				if (v.getJob().getType() != type && v.getJob().getWorkplace() != owner) {
					return false;
				}
			}
			return list.size() == amount;
		}
		
		public void attach(Villager v) {
			list.add(v);
		}
		
	}

	public ProductionBuilding() {
		workforce = new LinkedList<Position>();
		stock = new HashMap<Good,Integer>();
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
		return isFull() && hasInput();
	}
	
	public boolean hasInput() {
		for (Production prod : production) {
			Good input = prod.getInput();
			if (!(stock.containsKey(input) && stock.get(input)>=prod.getInputAmount())) {
				return false;
			}
		}
		return true;
	}

	public abstract void initializeWorkforce();
	
	public List<Production> getProduction() {
		return production;
	}

	public ProductionBuilding setProduction(List<Production> production) {
		this.production = production;
		return this;
	}
	
}
