package view;

import model.World;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class WorldRenderer implements Renderer {
	World world;
	Image worldImage;

	public WorldRenderer(World world) {
		int s = world.getLandSize();

		this.world = world;
		try {
			worldImage = Image.createOffscreenImage(s * world.getWidth(), s
					* world.getHeight());
			generateWorldImage();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		g.drawImage(worldImage, 0, 0);
	}

	private void generateWorldImage() throws SlickException {
		Graphics g = worldImage.getGraphics();
		int s = world.getLandSize();
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
				g.setColor(c);
				g.fillRect(x * s, y * s, s, s);
				g.flush();
			}
		}
	}
}
