package view;

import org.newdawn.slick.Graphics;

public interface Renderer {
	public void render(Graphics g);
	//TODO: Create abstraction layer between View and Graphics in order to port to android
}
