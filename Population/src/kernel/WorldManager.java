package kernel;


import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import model.Discoverable;
import model.Villager;
import model.World;
import model.nature.Land;

public class WorldManager {
	private static World world;

	private static final String world_file = "test.world";

	public static Land getLandAt(float x, float y) {
		int s = world.getLandSize();
		return world.get((int) x / s, (int) y / s);
	}

	public static Land getLandAt(Point p) {
		return getLandAt(p.getX(),p.getY());
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

	public static Collection<? extends Discoverable> getLandsAround(Entity entity, int visibilityRange) {
		LinkedList<Land> lands = new LinkedList<Land>();
		Point centre = entity.getLocation();
				
		int offset = visibilityRange*world.getLandSize();
		int debutX =  (int) (centre.getX() - offset);
		int debutY =  (int) (centre.getY() - offset);
		
		for (int x = debutX ; x < centre.getX() + offset; x++) {
			for (int y = debutY ; y < centre.getY() + offset; y++) {
				Point p = new Point(x,y);
				if (Point.manhattanDistance(p,centre) <= visibilityRange) {
					Land land = getLandAt(p);
					if (land != null)
						lands.add(land);
				}
			}
		}
				
		return lands;
	}




}
