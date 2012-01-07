package mapping;

import pop.*;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.*;

public class Map extends Entity {
	
	public TiledMap tm;
	
	public Map() {
		tm=null;
		try {
			tm = new TiledMap("C:\\Users\\Fanchon et Julien\\Desktop\\ISIMA\\eclipse-java-helios-SR1-win32-x86_64\\TMX\\grass.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	
	}

	public void render(Graphics g) {
		tm.render(0, 0);
	}
		
}
