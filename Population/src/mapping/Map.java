package mapping;

import java.awt.Rectangle;
import java.util.ArrayList;

import pop.*;

import mapping.Land.Type;

public class Map extends Entity {
	
	private ArrayList<ArrayList<Integer>> grid;
	
	public Map(int x, int y, int w, int h) {
		super(x,y,w,h);
		grid = new ArrayList<ArrayList<Integer>>(w/16);
		for (int k=0;k<w/16;k++) {
			grid.add(new ArrayList<Integer>(h/16));
			for (int l=0;l<h/16;l++) {
				grid.get(k).add(0);
			}
		}
		System.out.println(grid);
	}

	public void addLand(int x, int y, int w, int h, Type type) {
		
		
	}


	
}
