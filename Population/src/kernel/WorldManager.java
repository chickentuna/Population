package kernel;

import java.io.IOException;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import model.World;
import model.nature.Land;

public class WorldManager {
	private static World world;

	private static final String world_file = "test.world";

	public static Land getLand(float x, float y) {
		int s = world.getLandSize();
		return world.get((int) x / s, (int) y / s);
	}

	public static void init() {
		try {
			world = WorldParser.parseWorld(world_file);
		} catch (IOException e) {
			world = null;
			e.printStackTrace();
		}

	}

	public static void render(Graphics g) {
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				Color c;
				switch (world.get(x, y)) {
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

}
