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
	int scale = 1;
	final static int tileSize = 32;

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
		
		float w = worldImage.getWidth();
		float h = worldImage.getHeight();
		
		g.drawImage(worldImage, 0, 0, w*scale, h*scale, 0, 0, w, h);
	}

	private void generateWorldImage() throws SlickException {
		Graphics g = worldImage.getGraphics();
		int s = tileSize;
		scale = world.getLandSize() / s;
		for (int x = 0; x < world.getWidth(); x++) {
			for (int y = 0; y < world.getHeight(); y++) {
				int spriteIndex = Sprite.Missing;
				int under = Sprite.Missing;
				Type currentLandType = world.getLand(x, y).getType();

				int autoCode = decodeLand(currentLandType, x, y);

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
					int subCode = 0b1111;
					SpriteLoader.get(under).autoDraw(g, subCode, x * s, y * s, s, s);
				}

				// TODO: Add an autotile sprite called "missing autotile" and
				// draw it anyways

				if (spriteIndex != Sprite.Missing) {
					SpriteLoader.get(spriteIndex).autoDraw(g, autoCode, x * s, y * s, s, s);
				}

			}
			g.flush();
		}
	}

	/**
	 * 
	 * The bit field code is : NW NE SW SE N W E S
	 * 
	 */
	private int decodeLand(Type currentLandType, int x, int y) { 
		int landCode = 0;
		Land neighbour;

		neighbour = world.getLand(x, y - 1);
		landCode |= neighbourCodeCheck(neighbour, currentLandType, 0b1000);

		neighbour = world.getLand(x - 1, y);
		landCode |= neighbourCodeCheck(neighbour, currentLandType, 0b0100);

		neighbour = world.getLand(x + 1, y);
		landCode |= neighbourCodeCheck(neighbour, currentLandType, 0b0010);

		neighbour = world.getLand(x, y + 1);
		landCode |= neighbourCodeCheck(neighbour, currentLandType, 0b0001);

		int l = landCode;
		
		// North-West
		if ((landCode & 0b1100) == 0b1100) {
			neighbour = world.getLand(x - 1, y - 1);
			landCode |= cornerCodeCheck(neighbour, currentLandType, 0b1000_0000);
		}
		
		int a = landCode;
		
		// North-East
		if ((landCode & 0b1010) == 0b1010) {
			neighbour = world.getLand(x + 1, y - 1);
			landCode |= cornerCodeCheck(neighbour, currentLandType, 0b0100_0000);
		}
		
		int b = landCode;
		
		// South-West
		if ((landCode & 0b0101) == 0b0101) {
			neighbour = world.getLand(x - 1, y + 1);
			landCode |= cornerCodeCheck(neighbour, currentLandType, 0b0010_0000);
		}
		
		int c = landCode;
				
		// South-East
		if ((landCode & 0b0011) == 0b0011) {
			neighbour = world.getLand(x + 1, y + 1);
			landCode |= cornerCodeCheck(neighbour, currentLandType, 0b0001_0000);
		}
		
		int d = landCode;
		
		if (landCode < 0) {
			//System.out.println(Integer.toBinaryString(l));
			neighbour = world.getLand(x - 1, y - 1);
			//System.out.println("or with : "+cornerCodeCheck(neighbour, currentLandType, 0b1000_0000));
			
			//System.out.println(Integer.toBinaryString(a));
		}

		return landCode;
	}

	private int cornerCodeCheck(Land neighbour, Type currentLandType, int bit) {
		if (neighbour == null || ((neighbour.getType() == currentLandType) || (currentLandType == Land.Type.BEACH && neighbour.getType() == Type.SEA))) {
			return 0;
		}
		return bit;
	}

	private int neighbourCodeCheck(Land neighbour, Type currentLandType, int bit) {
		if (neighbour == null || ((neighbour.getType() == currentLandType) || (currentLandType == Land.Type.BEACH && neighbour.getType() == Type.SEA))) {
			return bit;
		}
		return 0;
	}
}
