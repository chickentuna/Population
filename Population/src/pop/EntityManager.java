package pop;

import java.util.ArrayList;

import mapping.Map;
import mapping.Ressource;
import mapping.Tiles;
import mapping.Tree;

public class EntityManager {

	public static ArrayList<Entity> entities;
	public static Map map;
	public static GoodMap ressources;

	public static void init() {
		entities = new ArrayList<Entity>();
		generateMap();
		ressources = new GoodMap(Good.PLANK,200);
		ressources.put(Good.WHEAT, 200);
	}

	public static Entity spawn(Entity o) {
		 entities.add(o);
		 if (o.isMapItem()) {
			 for (int k=0;k<o.getWidth();k++) {
				 for (int i=0;i<o.getHeight();i++) {
					 map.tm.setTileId(o.getMapX(), o.getMapY(), 1, o.getTileID(k,i));
				 }
			 }
		 }
		 return o;
	}

	public static boolean unspawn(Entity o) {
		o.destroy();
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
		map = new Map();
		entities.add(map);
		for (int k=0;k<map.tm.getWidth();k++) {
			for (int i=0;i<map.tm.getHeight();i++) {
				if (map.tm.getTileId(k, i, 1)==Tiles.TREE1) {
					spawn(new Tree(k*32,i*32));
				}
			}
		}
		
		
		
	}

	public static boolean freeArea(int x, int y, int w, int h) {
		
		int tile,floor;
		for (int k=0;k<w;k++) {
			for (int i=0;i<h;i++) {
				tile = map.tm.getTileId(x+k, y+i, 1);
				floor = map.tm.getTileId(x+k, y+i, 0);
				if (tile!=0 || Tiles.impracticable(floor, tile))
					return false;
			}
		}
		return true;
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
