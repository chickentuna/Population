package model;

import model.nature.Land;

public class World {
	private Land[][] terrain;
	private int land_size = 100;

	public World(int size) {
		this(size, size);
	}

	public World(int width, int height) {
		terrain = new Land[width][height];
	}

	public Land get(int x, int y) {
		if (x >= getWidth() || y >= getHeight() || x < 0 || y < 0)
			return null;
		return terrain[x][y];
	}

	public void set(int x, int y, Land type) {
		terrain[x][y] = type;
	}

	public int getLandSize() {
		return land_size;
	}

	public void setLandSize(int land_size) {
		this.land_size = land_size;
	}

	public int getWidth() {
		return terrain.length;
	}

	public int getHeight() {
		return terrain[0].length;
	}
}
