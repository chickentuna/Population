package pop;

import java.util.ArrayList;

import mapping.Land;
import mapping.Map;

public class EntityManager {

	public static ArrayList<Entity> entities;
	public static Map map;

	public static void init() {
		entities = new ArrayList<Entity>();
		generateMap();
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
				if (((ResidentialBuilding) e).spaceLeft())
					return (ResidentialBuilding) e;
			}
		}
		return null;
	}

	public static int size() {
		return entities.size();
	}

	public static Entity get(int k) {
		return entities.get(k);
	}

	public static void generateMap() {
		map = new Map(0,0,620,480);
		
	}
}
