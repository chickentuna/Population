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
