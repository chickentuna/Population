package pop;

import java.awt.Rectangle;

import org.newdawn.slick.Graphics;

public abstract class Entity {
	protected float x,y;
	protected int width;
	protected int height;
	protected int [][] tiles;

	public Entity(float x, float y, int w, int h) {
		this.x=x;
		this.y=y;
		width=w;
		height=h;
		//EntityManager.spawn(this);
	}
	public Entity(float x, float y) {
		this(x,y,0,0);
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
	public int getMapX() {
		return ((int)(x/32));
	}
	public int getMapY() {
		return ((int)(y/32));
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
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return width;
	}
	public boolean isMapItem() {
		return width>0;
	}
	public int getTileID(int x, int y) {
		return tiles[x][y];
	}
	public void setLocation(int x, int y) {
		this.x=x;
		this.y=y;	
	}	
	
}
