package mapping;

import pop.Building;
import pop.EntityManager;
import pop.LumberMill;


public class Tiles {

	public static final int TREE1 = 217;
	public static final int LUMBERMILL = 442;
	public static final int HOVEL = 314;

	public static boolean impracticable(int t, int i) {
		if (Boolean.parseBoolean(EntityManager.map.tm.getTileProperty(t, "floor", "false"))
				&& !Boolean.parseBoolean(EntityManager.map.tm.getTileProperty(i, "blocker", "false"))
				)
			return false;
		return true;
	}

	public static Building reqBuilding(int t) {
		if (EntityManager.map.tm.getTileProperty(t, "ressource", "none").equals("wood"))
			return new LumberMill();
		return null;
	}
	
}
