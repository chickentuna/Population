package model;

import model.nature.Land;

public class WorldManager {
	private static World world;
	
	public WorldManager() {
	
	}
	
	public Land getLand(int x, int y) {
		return world.get(x, y);
	}

	public static void init() {
				
	}
}
