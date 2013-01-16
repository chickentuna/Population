package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import model.nature.Land;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import kernel.Entity;
import kernel.WorldManager;

public class Villager extends Entity {

	public static final int speed = 1;

	private List<Behaviour> behaviours;
	protected State state = State.IDLE;
	protected float direction = 0;

	public Villager(int x, int y) {
		super((float) x, (float) y);
		behaviours = new LinkedList<Behaviour>();
		behaviours.add(Behaviour.STANDARD);

	}

	
	protected void step_foward() {
		float new_x = (float) (x + speed * Math.cos(direction));
		float new_y = (float) (y - speed * Math.sin(direction));
		Land l = WorldManager.getLand(x, y);
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
