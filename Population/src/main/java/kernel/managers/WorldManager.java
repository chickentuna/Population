package kernel.managers;

import io.WorldParser;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import kernel.Decision;
import kernel.DecisionAdapter;
import kernel.Entity;
import kernel.Locatable;
import kernel.Point;
import model.Producer;
import model.World;
import model.nature.Land;
import model.technology.Building;

public class WorldManager {
	private final String world_file = "test.world";
	private World world;
	private static WorldManager self = null;

	public static WorldManager get() {
		if (self == null) {
			self = new WorldManager();
		}
		return self;
	}

	public static void get(String string) {
		self = new WorldManager(string);

	}

	public WorldManager(String debug) {
		try {
			world = WorldParser.parseWorldFromString(debug);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private WorldManager() {
		try {
			world = WorldParser.parseWorldFromFile(world_file);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Land getLandAt(Point p) {
		return getLandAt(p.getX(), p.getY());
	}

	public Land getLandAt(float x, float y) {
		int s = world.getLandSize();
		if (x < 0)
			x -= world.getLandSize();
		if (y < 0)
			y -= world.getLandSize();

		return world.getLand((int) (x / (float) s), (int) (y / (float) s));
	}

	public Collection<Land> getLandsAround(Entity entity, float visibilityRange) {
		HashSet<Land> lands = new HashSet<Land>();
		List<Point> locs = getLocationsAround(entity, visibilityRange);

		for (Point p : locs) {
			Land l = getLandAt(p);
			if (l != null) {
				lands.add(l);
			}
		}
		return lands;

	}

	public Land getLandUnder(Entity entity) {
		return getLandAt(entity.getX(), entity.getY());
	}

	public World getWorld() {
		return world;

	}

	public List<Point> getLocationsAround(Entity entity, float dist) {
		LinkedList<Point> points = new LinkedList<>();

		Point centre = new Point(0, 0);

		if (dist < 0)
			dist = -dist;

		for (float x = -dist; x <= dist; x = getNextValue(x, dist)) {
			for (float y = -dist; y <= dist; y = getNextValue(y, dist)) {
				if (Point.manhattanDistance(centre, new Point(x, y)) <= dist) {
					Point p = new Point(entity.getX() + x * world.getLandSize(), entity.getY() + y * world.getLandSize());
					if (p.getX() >= 0 && p.getY() >= 0) {
						points.add(p);
					}
				}
			}
		}

		return points;
	}

	public float getNextValue(float x, float dist) {
		float n = x;
		
		if (n == dist)
			return n+1;
		
		
		if ( n < 0 ) {
			if (n == (int)n) {
				n+=1;
			} else {
				n = (int) n;
			}
		} else {
			n += 1;
		}
		
		if (n <= dist)
			n = (int) n;
		else
			n = dist;
		return n;
	}

	public Building getBuildingAt(float x, float y) {
		int s = world.getLandSize();
		return world.getBuilding((int) x / s, (int) y / s);
	}

	public Building getBuildingAt(Point p) {
		return getBuildingAt(p.getX(), p.getY());
	}

	public List<Decision> getProductionDecisionsAround(Entity entity, int dist) {
		LinkedList<Decision> decisions = new LinkedList<>();
		List<Point> locations = getLocationsAround(entity, dist);

		for (Point point : locations) {
			Producer param;
			param = getLandAt(point);
			if (param != null && param.getWeight() > 0) {
				decisions.add(new DecisionAdapter(param.getWeight(), point));
			}
		}

		return decisions;
	}

	public boolean onSameTile(Locatable l1, Locatable l2) {
		int s = world.getLandSize();
		int x1 = (int) l1.getX() / s;
		int y1 = (int) l1.getY() / s;
		int x2 = (int) l2.getX() / s;
		int y2 = (int) l2.getY() / s;

		return (x1 == x2) && (y1 == y2);
	}

	public int getHeight() {
		return world.getHeight() * world.getLandSize();
	}

	public int getWidth() {
		return world.getWidth() * world.getLandSize();
	}

}
