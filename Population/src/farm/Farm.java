package farm;

import pop.Building;
import pop.ProductionBuilding;

public abstract class Farm extends ProductionBuilding {

	public Farm() {
		type = Building.Type.FARM;
	}

}