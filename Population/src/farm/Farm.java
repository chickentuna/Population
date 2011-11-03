package farm;

import pop.Building;
import pop.ProductionBuilding;

public abstract class Farm extends ProductionBuilding {

	/**	NOTES
	 * residential
	 * production
	 * official buildings (law court etc)
	 * service buildings (leisure & health/hygiene, science buildings)
	 * infrastructure (roads, aqueducts, fountains)
	 * tourism (hotels)
	 * religion (church)
	 */
	
	
	
	public Farm() {
		type = Building.Type.FARM;
	}

}