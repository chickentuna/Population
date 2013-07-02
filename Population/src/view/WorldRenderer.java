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
				
				System.out.println(currentLandType.name() + " : "+Integer.toBinaryString(autoCode));
				
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
					SpriteLoader.get(under).autoDraw(g, 0b1111, x * s, y * s, s, s);
				}
				
				//TODO: Add an autotile sprite called "missing autotile" and draw it anyways
				
				if (spriteIndex != Sprite.Missing) {
					SpriteLoader.get(spriteIndex).autoDraw(g, autoCode, x * s, y * s, s, s);
				}

			}
			g.flush();
		}
	}

	/**
	 * 
	 * The bit field code is :
	 * NW NE SW SE N W E S
	 * 
	 */
	private byte decodeLand(Type currentLandType, int x, int y) { //TODO: Refactor + find a better way to deal with sand problem
		byte landCode = 0;
		Land neighbour;
		
		neighbour = world.getLand(x, y - 1);
		landCode |= neighbourCodeCheck(neighbour, currentLandType, 0b1000); 
		
		neighbour = world.getLand(x - 1, y);
		landCode |= neighbourCodeCheck(neighbour, currentLandType, 0b0100);
		
		neighbour = world.getLand(x + 1, y);
		landCode |= neighbourCodeCheck(neighbour, currentLandType, 0b0010);
		
		neighbour = world.getLand(x, y + 1);
		landCode |= neighbourCodeCheck(neighbour, currentLandType, 0b0001);
		
		//North-West
		if ((landCode & 0b1100) == 0b1100) {
			neighbour = world.getLand(x - 1, y - 1);
			landCode |= cornerCodeCheck(neighbour, currentLandType, 0b1000_0000); 
		}
		//North-East
		if ((landCode & 0b1010) == 0b1010) {
			neighbour = world.getLand(x + 1, y - 1);
			landCode |= cornerCodeCheck(neighbour, currentLandType, 0b0100_0000); 
		}
		//South-West
		if ((landCode & 0b0101) == 0b0101) {
			neighbour = world.getLand(x - 1, y + 1);
			landCode |= cornerCodeCheck(neighbour, currentLandType, 0b0010_0000); 
		}
		//South-East
		if ((landCode & 0b0011) == 0b0011) {
			neighbour = world.getLand(x + 1, y + 1);
			landCode |= cornerCodeCheck(neighbour, currentLandType, 0b0001_0000); 
		}
		
		return landCode;
	}

	private int cornerCodeCheck(Land neighbour, Type currentLandType, int bit) {
		if (neighbour == null || neighbour != null && ((neighbour.getType() == currentLandType)||(currentLandType == Land.Type.BEACH && neighbour.getType() == Type.SEA))) {
			return 0;
		}
		return bit;
	}

	private int neighbourCodeCheck(Land neighbour, Type currentLandType, int bit) {
		if (neighbour == null || neighbour != null && ((neighbour.getType() == currentLandType)||(currentLandType == Land.Type.BEACH && neighbour.getType() == Type.SEA))) {
			return bit;
		}
		return 0;
	}
}
