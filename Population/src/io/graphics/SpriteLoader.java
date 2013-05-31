package io.graphics;

import io.OS;

import java.util.HashMap;

import org.newdawn.slick.SlickException;

public class SpriteLoader {
	static HashMap<Integer, Sprite> cache = new HashMap<>();

	public static void init() { // First tries
		try {
			Sprite i = new Sprite("ressource" + OS.getSlash() + "Iconset2.png");
			int w = 24;
			int h = 24;
			int x = 7;
			int y = 15;
			Sprite Apple = i.getSubSprite(x * w, y * h, w, h);

			
			cache.put(Sprite.Apple, Apple);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public static Sprite get(int spriteIndex) {
		return cache.get(spriteIndex);

	}

}
