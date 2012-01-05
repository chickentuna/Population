package pop;

import org.newdawn.slick.Graphics;

public class Hovel extends ResidentialBuilding {
	
	public static final int CAPACITY = 5;
	
	public Hovel(float x, float y) {
		super(CAPACITY,x,y);
	}
	public void render(Graphics g) {
		g.drawRect(x, y, 10, 10);
	}
}
