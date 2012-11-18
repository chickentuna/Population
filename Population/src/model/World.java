package model;

import model.nature.Land;

public class World {
	private Land[][] terrain;
	
	public World(int size) {
		this(size, size);
	}
	public World(int width, int height) {
		terrain = new Land[width][height];
	}
	
	public Land get(int x, int y) {
		return terrain[x][y];
	}
}
