package mapping;

import java.awt.Rectangle;
import java.util.ArrayList;
import pop.*;
import mapping.Land.Type;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.*;

public class Map extends Entity {
	
	public TiledMap tm;
	
	public Map(int x, int y, int w, int h) {
		tm=null;
		try {
			tm = new TiledMap("C:\\Program Files (x86)\\Tiled\\examples\\desert.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		System.out.println(tm.getTilesLocation());
		tm.setTileId(0, 0, 0, 1);
	}

	public void render(Graphics g) {
		tm.render(0, 0);
	}
	
	public void addLand(int x, int y, int w, int h, Type type) {
		
		
	}


	
}
