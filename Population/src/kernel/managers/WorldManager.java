package kernel.managers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;

import kernel.Entity;
import kernel.Point;
import kernel.WorldParser;
import model.Discoverable;
import model.World;
import model.nature.Land;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import technology.Building;

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

	public WorldManager() {
		try {
			world = WorldParser.parseWorld(new File(world_file));
		} catch (IOException e) {
			world = null;
			e.printStackTrace();
		}

	}

	public Land getLandAt(Point p) {
		return getLandAt(p.getX(), p.getY());
	}

	public Land getLandAt(float x, float y) {
		int s = world.getLandSize();
		return world.getLand((int) x / s, (int) y / s);
	}

	public Building getBuildingAt(float x, float y) {
		int s = world.getLandSize();
		return world.getBuilding((int) x / s, (int) y / s);
	}

	public void render(Graphics g) {
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				Color c;
				switch (world.getLand(x, y)) {
				case BEACH:
					c = Color.yellow;
					break;
				case HILL:
					c = Color.white;
					break;
				case PLAIN:
					c = Color.green;
					break;
				case LAKE:
				case SEA:
					c = Color.blue;
					break;
				case WOOD:
					c = Color.darkGray;
					break;
				default:
					c = Color.black;
				}
				int s = world.getLandSize();
				g.setColor(c);
				g.fillRect(x * s, y * s, s, s);

			}
		}

	}

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
