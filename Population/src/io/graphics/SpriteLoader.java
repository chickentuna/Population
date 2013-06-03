package io.graphics;

import io.OS;

import java.util.HashMap;

import org.newdawn.slick.SlickException;

public class SpriteLoader {
	static HashMap<Integer, Sprite> cache = new HashMap<>();

	public static void init() { // First tries
		try {
			Sprite i = new Sprite("ressource" + OS.getSlash() + "Iconset.png");
			int w = 24;
			int h = 24;

			cache.put(Sprite.Clefairy, i.getSubSprite(6 * w, 91 * h, w, h));
			cache.put(Sprite.Missing, i.getSubSprite(8 * w, 134 * h, w, h));
			cache.put(Sprite.Apple, i.getSubSprite(12 * w, 109 * h, w, h));
			cache.put(Sprite.Crab, i.getSubSprite(5 * w, 89 * h, w, h));
			cache.put(Sprite.Fish, i.getSubSprite(8 * w, 89 * h, w, h));
			cache.put(Sprite.Log, i.getSubSprite(1 * w, 11 * h, w, h));
			cache.put(Sprite.Plank, i.getSubSprite(2 * w, 11 * h, w, h));
			cache.put(Sprite.Ore, i.getSubSprite(0 * w, 16 * h, w, h));
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public static Sprite get(int spriteIndex) {
		Sprite res = cache.get(spriteIndex);
		if (res == null)
			res = cache.get(Sprite.Missing);
		return res;
	}

}
