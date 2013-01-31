package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.nature.Land;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import kernel.Entity;
import kernel.managers.WorldManager;

import static model.VState.*;

public class Villager extends Entity {

	public static final int WALK_SPEED = 1; 

	private List<Behaviour> behaviours;
	
	protected VState state = IDLE;
	protected float direction = 0;
	protected Building home = null;

	public Villager(int x, int y) {
		super((float) x, (float) y);
		behaviours = new LinkedList<Behaviour>();
	
	}
	
	public void adoptBehaviour(Behaviour b) {
		behaviours.add(b);
	}

	
	protected void step_foward() {
		float new_x = (float) (x + WALK_SPEED * Math.cos(direction));
		float new_y = (float) (y - WALK_SPEED * Math.sin(direction));
		Land l = WorldManager.get().getLandAt(x, y);
		if (l != null) {
			x = new_x;
			y = new_y;
		}
	}

	public void update() {
		Iterator<Behaviour> it = behaviours.iterator();
		while (it.hasNext()) {
			it.next().execute(this);
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(x, y, 1, 1);
	}



}
