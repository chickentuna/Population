package pop;

import mapping.Tiles;

import org.newdawn.slick.Graphics;

public class Hovel extends ResidentialBuilding {
	
	public static final int CAPACITY = 5;
	
	public Hovel(float x, float y) {
		super(CAPACITY,x,y);
		height=1;
		width=1;
		tiles = new int[1][1];
		tiles[0][0]=Tiles.HOVEL;
	}
	public Hovel() {
		this(0,0);
	}
	public void render(Graphics g) {
		
	}
}
