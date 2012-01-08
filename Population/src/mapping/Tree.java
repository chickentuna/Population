package mapping;

import pop.Entity;
import pop.LumberMill;
import pop.ProductionBuilding;

public class Tree extends Entity implements Ressource{

	public Tree(float x, float y) {
		super(x,y);
	}

	@Override
	public ProductionBuilding getReqBuilding() {
		return new LumberMill();
	}

}
