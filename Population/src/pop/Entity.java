package pop;

import java.awt.Rectangle;

public abstract class Entity {
	protected int x,y;
	
	public Entity(int x, int y) {
		this.x=x;
		this.y=y;
		EntityManager.spawn(this);
	}
	public Entity() {
		this(0,0);
	}
	
	public boolean collidesWith(Entity other) {
		return getBounds().intersects(other.getBounds());
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,1,1);
		//Width & height will be regulated by the sprite
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void step() {
		
	}
	
	
}
