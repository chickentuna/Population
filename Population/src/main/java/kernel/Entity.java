package kernel;

import java.awt.Rectangle;

public abstract class Entity implements Locatable {
	protected float x,y;
	protected int width;
	protected int height;
	
	public Entity(float x, float y, int w, int h) {
		this.x=x;
		this.y=y;
		width=w;
		height=h;
	}
	public Entity(float x, float y) {
		this(x,y,0,0);
	}
	
	public void destroy() {
	}
	
	public boolean collidesWith(Entity other) {
		return getBounds().intersects(other.getBounds());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
		// Width & height will (not?)be regulated by the sprite
	}
	
	public Point getLocation() {
		return new Point(getX(),getY());
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	
	public void update() {
	}
	
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public void setLocation(int x, int y) {
		this.x=x;
		this.y=y;	
	}
	
}
