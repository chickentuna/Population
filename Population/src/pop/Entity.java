package pop;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;

public abstract class Entity {
	protected float x,y;
	
	public Entity(float x, float y) {
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
		return new Rectangle((int)x,(int)y,1,1);
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
