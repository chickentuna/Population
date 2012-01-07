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
		entities.add(map);
		map.tm.setTileId(4, 4, 1, Tiles.TREE1);
		spawn(new Tree(4*32,4*32));
		
	}

	public static boolean freeArea(int x, int y, int w, int h) {
		
		int tile;
		for (int k=0;k<w;k++) {
			for (int i=0;i<h;i++) {
				tile = map.tm.getTileId(x+k, y+i, 1);
				
				if (tile!=0)
					return false;
			}
		}
		return true;
	}
}
