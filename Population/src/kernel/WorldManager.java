package kernel;

import java.io.IOException;

import org.newdawn.slick.Graphics;

import model.World;
import model.nature.Land;

public class WorldManager {
	private static World world;	
	
	private static final String world_file = "test.world";
	
	
	public static Land getLand(int x, int y) {
		return world.get(x, y);
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
		
		
	}

		
}
