package io.graphics;

import java.util.HashMap;

import kernel.Point;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Sprite extends Image {

	// TODO: enum ?
	public static final int Missing = -1;
	public static final int None = 0;
	public static final int Clefairy = 1;
	public static final int Apple = 2;
	public static final int Crab = 3;
	public static final int Fish = 4;
	public static final int Log = 5;
	public static final int Ore = 6;
	public static final int Plank = 7;
	public static final int Stone = 8;
	public static final int Wheat = 9;

	// Terrain
	public static final int Hills = 10;
	public static final int Plains = 11;
	public static final int Woods = 12;
	public static final int Waters = 13;
	public static final int Sand = 14;

	private boolean autotile = false;
	private int tileSize = 32;
	private int imageCount = 1;

	// TODO : maybe : private int id ? from the above list

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

	public void autoDraw(Graphics g, byte autoCode, int x, int y, int w, int h) {
		if (autotile) {
			int subW = getWidth() / 4;
			int subH = getHeight() / 6;		

			Point[] corners = getCorners(autoCode);
			int offset_x = 0;
			int offset_y = 0;
			for (int i = 0; i < 4; i++) {
				Sprite s = getSubSprite((int) corners[i].getX() * subW, (int) corners[i].getY() * subH, subW, subH);
				g.drawImage(s, x + offset_x, y + offset_y, w/2, h/2, 0, 0, subW, subH);
				offset_x += w;
				if (offset_x > w) {
					offset_x = 0;
					offset_y += h;
				}
			}

		} else {
			//g.drawImage(SpriteLoader.get(Sprite.Missing), (float) x, (float) y, (float) w, (float) h, 0, 0, getWidth(), getHeight());
			throw new RuntimeException("Auto-drawing a Sprite that is not an autoTile or Autotile not found !");
		}

	}

	private Point[] getCorners(byte autoCode) {
		return autoCodeMap.get(new Integer(autoCode));
	}

	private static HashMap<Integer, Point[]> autoCodeMap = new HashMap<>();

	static {
		addCode(0b0000, 1, 2, 5, 6);
		addCode(0b0001, 9, 12, 13, 16);
		addCode(0b0010, 9, 10, 21, 22);
		addCode(0b0011, 9, 10, 13, 14);
		addCode(0b0100, 11, 12, 15, 16);
		addCode(0b0101, 11, 12, 15, 16);
		addCode(0b0110, 10, 11, 22, 23);
		addCode(0b0111, 10, 11, 14, 15);
		addCode(0b1000, 17, 20, 21, 24);
		addCode(0b1001, 13, 16, 17, 20);
		addCode(0b1010, 17, 18, 21, 22);
		addCode(0b1011, 13, 14, 17, 18);
		addCode(0b1100, 19, 20, 23, 24);
		addCode(0b1101, 15, 16, 19, 20);
		addCode(0b1110, 18, 19, 22, 23);
		addCode(0b1111, 14, 15, 18, 19);
	}

	private static void addCode(int code, int a, int b, int c, int d) {
		Point[] abcd = { new Point((a - 1) % 4, (a - 1) / 4), new Point((b - 1) % 4, (b - 1) / 4), new Point((c - 1) % 4, (c - 1) / 4), new Point((d - 1) % 4, (d - 1) / 4) };
		autoCodeMap.put(code, abcd);
	}

}
