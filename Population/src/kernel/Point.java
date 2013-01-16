package kernel;

import java.util.Collection;

public class Point {
	private final float x;
	private final float y;
	
	public Point(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}

	public Point withOffset(int amountX, int amountY) {
		return new Point(x+amountX,y+amountY);
	}
	
}
