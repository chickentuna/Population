package kernel.managers;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import kernel.Decision;
import kernel.DecisionAdapter;
import kernel.Entity;
import kernel.Locatable;
import kernel.Point;
import kernel.WorldParser;
import model.Producer;
import model.World;
import model.nature.Land;
import model.technology.BType;
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

	public WorldManager(String debug) {
		this();
	}

	private WorldManager() {
		try {
			world = WorldParser.parseWorld(new File(world_file));
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

	public Building getBuildingAt(float x, float y) {
		int s = world.getLandSize();
		return world.getBuilding((int) x / s, (int) y / s);
	}

	public Collection<Land> getLandsAround(Entity entity, int visibilityRange) {
		LinkedList<Land> lands = new LinkedList<Land>();
		Point centre = entity.getLocation();

		int offset = visibilityRange * world.getLandSize();
		int debutX = (int) (centre.getX() - offset);
		int debutY = (int) (centre.getY() - offset);

		for (int x = debutX; x <= centre.getX() + offset; x += world.getLandSize()) {
			for (int y = debutY; y <= centre.getY() + offset; y += world.getLandSize()) {
				Point p = new Point(x, y);
				if (Point.manhattanDistance(p, centre) <= visibilityRange * world.getLandSize()) {
					Land land = getLandAt(p);
					if (land != null) {
						lands.add(land);
					}
				}
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

	public static void get(String string) {
		self = new WorldManager("debug");

	}

	public Building getBuildingUnder(Entity e) {
		return getBuildingAt(e.getX(), e.getY());
	}

	public List<Point> getLocationsAround(Entity entity, int dist) {
		LinkedList<Point> points = new LinkedList<>();

		Point centre = new Point(0,0);

		for (int x = -dist; x <= dist; x++) {
			for (int y = -dist; y <= dist; y++) {
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

	public Building getBuildingAt(Point p) {
		return getBuildingAt(p.getX(), p.getY());
	}

	public List<Decision> getProductionDecisionsAround(Entity entity, int dist) {
		LinkedList<Decision> decisions = new LinkedList<>();
		List<Point> locations = getLocationsAround(entity, dist);

		Iterator<Point> it = locations.iterator();
		while (it.hasNext()) {
			Point point = it.next();
			Producer param = null;
			final Building b = getBuildingAt(point);
			if (b != null && b.getType() == BType.PRODUCTION) {
				param = (Producer) b;
			} else {
				final Land l = getLandAt(point);

				if (l != null) {
					param = l;
				}
			}
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

}
