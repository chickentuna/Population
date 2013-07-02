package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import model.World;
import model.nature.Land;
import model.nature.Land.Type;

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


	private void generateWorldImage() throws SlickException {
		Graphics g = worldImage.getGraphics();
		int s = world.getLandSize();
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				int spriteIndex = Sprite.Missing;
				int under = Sprite.Missing;
				Type currentLandType = world.getLand(x, y).getType();
			
				byte autoCode = decodeLand(currentLandType, x, y);
				switch (currentLandType) {
				case BEACH:
					under = Sprite.Plains;
					spriteIndex = Sprite.Sand;
					break;
				case HILL:
					under = Sprite.Plains;
					spriteIndex = Sprite.Hills;
					break;
				case PLAIN:
					autoCode = 0b1111;
					spriteIndex = Sprite.Plains;
					break;
				case LAKE:
					under = Sprite.Plains;
					spriteIndex = Sprite.Waters;
					break;
				case SEA:
					under = Sprite.Sand;
					spriteIndex = Sprite.Waters;
					break;
				case WOOD:
					under = Sprite.Plains;
					spriteIndex = Sprite.Woods;
					break;
				default:

				}
				
				if (under != Sprite.Missing) {
					SpriteLoader.get(under).autoDraw(g, (byte)0b1111, x * s, y * s, s, s);
				}
				
				//TODO: Add an autotile sprite called "missing autotile" and draw it anyways
				
				if (spriteIndex != Sprite.Missing) {
					SpriteLoader.get(spriteIndex).autoDraw(g, autoCode, x * s, y * s, s, s);
				}

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
