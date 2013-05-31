package io.graphics;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends Image {

	// TODO: enum ?
	public static final int Missing = -1;
	public static final int None = 0;
	public static final int Clefairy = 1;


	private boolean autotile = false;
	private int tileSize = 32;
	private int imageCount = 1;

	public Sprite(String path) throws SlickException {
		super(path);
	}

	public Sprite(Image img) {
		super(img);
	}

	public Sprite getSubSprite(int x, int y, int w, int h) {
		Image a = getSubImage(x, y, w, h);
		return new Sprite(a);
	}

	public void setTileSize(int size) {
		tileSize = size;
	}

	public int getTileSize() {
		return tileSize;
	}

	public boolean isAutotile() {
		return autotile;
	}

	public Sprite setAutotile(boolean autotile) {
		this.autotile = autotile;
		return this;
	}

	public boolean isAnimated() {
		return imageCount == 1;
	}

}
