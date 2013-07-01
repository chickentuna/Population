package io.graphics;

import java.io.File;
import java.util.HashMap;

import org.newdawn.slick.SlickException;

public class SpriteLoader {
	static HashMap<Integer, Sprite> cache = new HashMap<>();
	static Sprite sheet;
	static int spriteWidth;
	static int spriteHeight;

	public static void init() { // First tries
		try {
			sheet = new Sprite("resource" + File.separatorChar + "IconSet.png");
			spriteWidth = 24;
			spriteHeight = 24;

			cache.put(Sprite.Clefairy, 	fromSheet(6, 91));
			cache.put(Sprite.Missing, 	fromSheet(8, 134));
			cache.put(Sprite.Apple, 	fromSheet(12, 109));
			cache.put(Sprite.Crab, 		fromSheet(5, 89));
			cache.put(Sprite.Fish, 		fromSheet(8, 89));
			cache.put(Sprite.Log, 		fromSheet(1,11));
			cache.put(Sprite.Plank, 	fromSheet(2,11));
			cache.put(Sprite.Stone, 	fromSheet(0, 16));
			cache.put(Sprite.Ore, 		fromSheet(1, 16));
			cache.put(Sprite.Wheat, 	fromSheet(13, 4));
			
			sheet = new Sprite("resource" + File.separatorChar + "Terrain.png");
			spriteWidth = 64;
			spriteHeight = 96;
			cache.put(Sprite.Hills, 	fromSheet(2, 1).setAutotile(true));
			cache.put(Sprite.Woods, 	fromSheet(1, 0).setAutotile(true));
			cache.put(Sprite.Sand, 	fromSheet(3, 2).setAutotile(true));
			cache.put(Sprite.Waters, 	fromSheet(4, 0).setAutotile(true));
			cache.put(Sprite.Plains, 	fromSheet(3, 0).setAutotile(true));
			

		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	private static Sprite fromSheet(int x, int y) {
		return sheet.getSubSprite(x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);
	}

	public static Sprite get(int spriteIndex) {
		Sprite res = cache.get(spriteIndex);
		if (res == null)
			res = cache.get(Sprite.Missing);
		return res;
	}

}
