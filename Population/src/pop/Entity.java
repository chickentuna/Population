package pop;

import java.awt.Rectangle;

public class Entity {
	protected int x,y;
	protected Rectangle bounds;
	
	public Entity(int x, int y) {
		this.x=x;
		this.y=y;
		bounds = new Rectangle(x,y,1,1);
	}
	
	public void collidesWith(Entity other) {
		return bounds.intersect(other.getBounds());
	}
	
	public Rectangle getBounds() {
		return bounds;
	}
}
