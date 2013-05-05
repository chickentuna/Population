package kernel.managers;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import kernel.Entity;
import kernel.Point;
import kernel.WorldParser;
import model.Discoverable;
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

	// TODO: If not all forest tiles are the sameat, the used tile should me
	// save somwhere instead of recalculated. -> Make a large graphic upon
	// parse, then render that

	public Collection<? extends Discoverable> getLandsAround(Entity entity,
			int visibilityRange) {
		LinkedList<Land> lands = new LinkedList<Land>();
		Point centre = entity.getLocation();

		int offset = visibilityRange * world.getLandSize();
		int debutX = (int) (centre.getX() - offset);
		int debutY = (int) (centre.getY() - offset);

		for (int x = debutX; x <= centre.getX() + offset; x += world
				.getLandSize()) {
			for (int y = debutY; y <= centre.getY() + offset; y += world
					.getLandSize()) {
				Point p = new Point(x, y);
				if (Point.manhattanDistance(p, centre) <= visibilityRange
						* world.getLandSize()) {
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

}
