package pop;


import java.util.LinkedList;
import java.util.List;

public abstract class ProductionBuilding extends Building {
	
	protected GoodMap stock;
	protected List<Position> workforce;
	protected Production production;
	protected State state;
	protected float timer;
	
	public enum State { IDLE, ACTIVE }	
	
	public ProductionBuilding() {
		workforce = new LinkedList<Position>();
		stock = new GoodMap();
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
				for (Villager v : pos.getWorkers()) {
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
			for (Villager v : pos.getWorkers()) {
				if ( !v.collidesWith(this) ) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean hasInput() {
		GoodMap input = production.getInput();
		if (input !=null) {
			for (Good good : input.keySet()) {
				if (!stock.containsKey(good) || stock.get(good) < input.get(good) ) {
					return false;
				}
			}
		}
		return true;
	}

	public abstract void initializeWorkforce();
	
	public Production getProduction() {
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
