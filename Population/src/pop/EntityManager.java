package pop;

import java.util.ArrayList;

public class EntityManager {
	
	private static ArrayList<Entity> entities;  
	
	public static boolean spawn(Entity o) {
		return entities.add(o);
	}
	public static boolean unspawn(Entity o) {
		return entities.remove(o);
	}
	
}
