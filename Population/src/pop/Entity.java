package pop;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;

public abstract class Entity {
	protected float x,y;
	protected int width;
	protected int height;
	

	public Entity(float x, float y, int w, int h) {
		this.x=x;
		this.y=y;
		width=w;
		height=h;
		EntityManager.spawn(this);
	}
	public Entity(float x, float y) {
		this(x,y,1,1);
	}
	public Entity() {
		this(0,0);
	}
	
	public boolean collidesWith(Entity other) {
		return getBounds().intersects(other.getBounds());
	}
	public boolean collisionFree() {
		for (Entity e : EntityManager.entities) {
			if (e instanceof Solid && e!=this && collidesWith(e)) {
				return false;				
			}
		}
		return true;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
		//Width & height will be regulated by the sprite
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void step() {
		
	}
	public void update() {
	}
	public void render(Graphics g) {
	}

	
	
}
