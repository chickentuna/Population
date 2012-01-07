package mapping;

import pop.Building;
import pop.Entity;
import pop.LumberMill;

public class Tree extends Entity implements Ressource{

	public Tree(float x, float y) {
		super(x,y);
	}

	@Override
	public Building getReqBuilding() {
		return new LumberMill();
	}

}
