package pop;

import java.awt.Rectangle;

public class Land {
	
	private Rectangle bounds;
	private Type type;
	
	public enum Type { GRASS, FARMLAND, STONEFIELD}
	
	public Land setBounds(Rectangle bounds) {
		this.bounds = bounds;
		return this;
	}

	public Rectangle getBounds() {
		return bounds;
	}

	public Land setType(Type type) {
		this.type = type;
		return this;
	}

	public Type getType() {
		return type;
	}
	
}
