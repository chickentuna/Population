package kernel;

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
	
	public static int manhattanDistance(Point p1, Point p2) {
		return (int) (Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY()));
		
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public float directionTo(Point other) {
		//TODO: direction algo.
		return 0;
	}
	
}
