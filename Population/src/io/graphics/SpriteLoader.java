package io.graphics;

import io.OS;

import java.util.HashMap;

import org.newdawn.slick.SlickException;

public class SpriteLoader {
	static HashMap<String, Sprite> cache = new HashMap<>();

	static void init() { //First tries
		try {
			Sprite i = new Sprite("ressource" + OS.getSlash() + "worldmap1.png");
			int tx = 16 * 3;
			int ty = 16 * 4;
			Sprite WOOD = i.getSubSprite(0, 3 * ty, tx, ty);
			Sprite PLAIN = i.getSubSprite(0, 2 * ty, tx, ty);
			Sprite HILL = i.getSubSprite(2 * tx, 2 * ty, tx, ty);
			Sprite BEACH = i.getSubSprite(3 * tx, 0, tx, ty);

			cache.put("WOOD", WOOD.setAutotile(true));
			cache.put("PLAIN", PLAIN.setAutotile(true));
			cache.put("HILL", HILL.setAutotile(true));
			cache.put("BEACH", BEACH.setAutotile(true));
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	public static Sprite get(String name) {
		return cache.get(name);

	}

}
