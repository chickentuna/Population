package kernel.managers;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import kernel.Entity;
import kernel.Point;
import kernel.WorldParser;
import model.Discoverable;
import model.World;
import model.nature.Land;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import technology.Building;

public class WorldManager {
	private final String world_file = "test.world";
	private Image worldImage;
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
			int s = world.getLandSize();
			worldImage = Image.createOffscreenImage(s * world.getWidth(), s
					* world.getHeight());
			generateWorldImage();
		} catch (Exception e) {
			e.printStackTrace();
			world = null;
			worldImage = null;
		}

	}

	private void generateWorldImage() throws SlickException {
		Graphics g = worldImage.getGraphics();
		int s = world.getLandSize();

		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				Land l = world.getLand(x, y);

				Point coord = tileCoordsFor(x, y);
				Sprite trn = SpriteLoader.get(l.name());
				int t = trn.getTileSize();
				g.drawImage(trn, x * s, y * s, s, s, coord.getX() * t,
						coord.getY() * t, coord.getX() * t + t, coord.getX()
								* t + t);
				g.flush();
			}
		}
	}

	private Point tileCoordsFor(int x, int y) {
		byte b = 0;
		int tx = 0, ty = 0;
		Land l = world.getLand(x, y);
		if (world.getLand(x - 1, y) == l)
			b |= 1;
		if (world.getLand(x + 1, y) == l)
			b |= 2;
		if (world.getLand(x, y - 1) == l)
			b |= 4;
		if (world.getLand(x, y + 1) == l)
			b |= 8;

		if ((b & 4) == 0)
			tx = 1;

		return new Point(tx, ty);
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

	public void render(Graphics g) {
		g.drawImage(worldImage, 0, 0);
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
