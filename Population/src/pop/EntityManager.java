package pop;

import java.util.ArrayList;

public class EntityManager {

	public static ArrayList<Entity> entities;


	public static void init() {
		entities = new ArrayList<Entity>();
	}

	public static Entity spawn(Entity o) {
		entities.add(o);
		return o;
	}

	public static boolean unspawn(Entity o) {
		o.destroy();
		return entities.remove(o);
	}

	public static int size() {
		return entities.size();
	}

	public static Entity get(int k) {
		return entities.get(k);
	}

	public static ArrayList<Entity> get(Class clazz) {
		ArrayList<Entity> array = new ArrayList<Entity>();
		for (int k=0;k<entities.size();k++) {
			if (clazz.isInstance(entities.get(k))) {
				array.add(entities.get(k));
			}
		}
		return array;
	}

}
