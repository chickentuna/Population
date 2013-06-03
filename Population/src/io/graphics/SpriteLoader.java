package io.graphics;

import io.OS;

import java.util.HashMap;

import org.newdawn.slick.SlickException;

public class SpriteLoader {
	static HashMap<Integer, Sprite> cache = new HashMap<>();
	static Sprite sheet;
	static int spriteWidth;
	static int spriteHeight;

	public static void init() { // First tries
		try {
			sheet = new Sprite("ressource" + OS.getSlash() + "Iconset.png");
			spriteWidth = 24;
			spriteHeight = 24;

			cache.put(Sprite.Clefairy, fromSheet(6, 91));
			cache.put(Sprite.Missing, fromSheet(8, 134));
			cache.put(Sprite.Apple, fromSheet(12, 109));
			cache.put(Sprite.Crab, fromSheet(5, 89));
			cache.put(Sprite.Fish, fromSheet(8, 89));
			cache.put(Sprite.Log, fromSheet(1,11));
			cache.put(Sprite.Plank, fromSheet(2,11));
			cache.put(Sprite.Ore, fromSheet(0, 16));

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
