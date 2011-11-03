package pop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ProductionBuilding extends Building {
	
	public class Position {
		
		private int amount;
		private int actual;

		public Position(int amount) {
			this.amount = amount;
			this.actual = 0;
		}
		
		public boolean isFull() {
			return amount == actual;
		}

		public Integer set(Integer actual) {
			return this.actual = actual;
		}
		
		
	}

	protected Map<Good,Integer> stock;
	protected Land land;
	protected Map<Job.Type, Position> workforce;
	protected List<Production> production;
	
	public ProductionBuilding() {
		workforce = new HashMap<Job.Type, Position>();
		stock = new HashMap<Good,Integer>();
		initializeWorkforce();
	}
	
	public boolean isFull() {
		for (Position pos : workforce.values()) {
			if (!pos.isFull()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean can() {
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

	public Land getLand() {
		return land;
	}
	
	public abstract void initializeWorkforce();
	
	public List<Production> getProduction() {
		return production;
	}
	public ProductionBuilding setLand(Land land) {
		this.land = land;
		return this;
	}
	public ProductionBuilding setProduction(List<Production> production) {
		this.production = production;
		return this;
	}
	
}
