package pop;

import java.util.ArrayList;

public class EntityManager {
	
	public static ArrayList<Entity> entities;
	
	public static void init() {
		entities = new ArrayList<Entity>();
	}
	
	public static boolean spawn(Entity o) {
		return entities.add(o);
	}
	public static boolean unspawn(Entity o) {
		return entities.remove(o);
	}

	public static ResidentialBuilding getFreeHome() {
		for (Entity e : entities) {
			if (e instanceof ResidentialBuilding) {
				if (!((ResidentialBuilding) e).isFull())
					return (ResidentialBuilding) e;
			}
		}
		return null;
	}
	
}
