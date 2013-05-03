package io;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class SpriteLoader {
	static HashMap<String, Image> cache = new HashMap<>();

	static { // TODO: change this yucky code of nauseating grossness
		try {
			Image i = new Image("ressource" + OS.getSlash() + "worldmap1.png");
			int t = 16;
			Image WOOD = i.getSubImage(0, 13 * t, 3 * t, 3 * t);
			Image PLAIN = i.getSubImage(0, 9 * t, 3 * t, 3 * t);
			cache.put("WOOD", WOOD);
			cache.put("PLAIN", PLAIN);
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

}
