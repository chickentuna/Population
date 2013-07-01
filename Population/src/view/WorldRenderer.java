package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import model.World;
import model.nature.Land;
import model.nature.Land.Type;

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
			worldImage = Image.createOffscreenImage(s * world.getWidth(), s * world.getHeight());
			generateWorldImage();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {
		g.drawImage(worldImage, 0, 0);
	}

	@Deprecated
	private void generateCrappyWorldImage() throws SlickException {
		Graphics g = worldImage.getGraphics();
		int s = world.getLandSize();
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				Color c;
				switch (world.getLand(x, y).getType()) {
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

	private void generateWorldImage() throws SlickException {
		Graphics g = worldImage.getGraphics();
		int s = world.getLandSize();
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				int spriteIndex = -1;
				Type currentLandType = world.getLand(x, y).getType();
				switch (currentLandType) {
				case BEACH:
					spriteIndex = Sprite.Sand;
					break;
				case HILL:
					spriteIndex = Sprite.Hills;
					break;
				case PLAIN:
					spriteIndex = Sprite.Plains;
					break;
				case LAKE:
				case SEA:
					spriteIndex = Sprite.Waters;
					break;
				case WOOD:
					spriteIndex = Sprite.Woods;
					break;
				default:

				}
				Sprite tileset = SpriteLoader.get(spriteIndex);
				byte autoCode = decodeLand(currentLandType, x, y);
				tileset.autoDraw(g, autoCode, x * s, y * s, s, s);

			}
			g.flush();
		}
	}

	private byte decodeLand(Type currentLandType, int x, int y) {
		byte landCode = 0;
		Land neighbour;
		neighbour = world.getLand(x, y - 1);
		if (neighbour != null && neighbour.getType() == currentLandType) {
			landCode |= 0b1000;
		}
		neighbour = world.getLand(x - 1, y);
		if (neighbour != null && neighbour.getType() == currentLandType) {
			landCode |= 0b0100;
		}
		neighbour = world.getLand(x + 1, y);
		if (neighbour != null && neighbour.getType() == currentLandType) {
			landCode |= 0b0010;
		}
		neighbour = world.getLand(x, y + 1);
		if (neighbour != null && neighbour.getType() == currentLandType) {
			landCode |= 0b0001;
		}
		return landCode;
	}
}
