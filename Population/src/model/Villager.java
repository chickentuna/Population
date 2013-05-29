package model;

import static model.VState.IDLE;

import java.util.LinkedList;
import java.util.Iterator;
import java.util.List;
import kernel.Entity;
import kernel.Point;
import kernel.managers.WorldManager;
import model.behaviour.Behaviour;
import model.nature.Land;
import model.nature.Produce;

import org.newdawn.slick.Graphics;

public class Villager extends Entity {

	public static final int WALK_SPEED = 1;

	private List<Behaviour> behaviours;
	private List<Behaviour> toAdopt;
	private List<Behaviour> toAbandon;

	
	private VState state = IDLE;
	private float direction = 0;
	private Produce collecting;

	public Villager(int x, int y) {
		super((float) x, (float) y);
		behaviours = new LinkedList<Behaviour>();
		toAbandon = new LinkedList<Behaviour>();
		toAdopt = new LinkedList<Behaviour>();
	}
	
	public void adoptBehaviour(Behaviour b) {
		toAdopt.add(b);
	}

	public void abandonBehaviour(Behaviour behaviour) {
		toAbandon.add(behaviour);
	}

	//TODO: implement path_finding ? Or rather a safe guard for the GOING Behaviour
	public void step_foward() {
		float new_x = (float) (x + WALK_SPEED * Math.cos(direction));
		float new_y = (float) (y - WALK_SPEED * Math.sin(direction));
		Land l = WorldManager.get().getLandAt(new_x, new_y);
		if (l != null) {
			x = new_x;
			y = new_y;
		}
	}

	public void update() {
		refreshBehaviours();
		Iterator<Behaviour> it = behaviours.iterator();
		while (it.hasNext()) {
			Behaviour b = it.next();
			b.execute(this);
		}
	}

	private void refreshBehaviours() {
		Iterator<Behaviour> it = toAdopt.iterator();
			while (it.hasNext()) {
			Behaviour b = it.next();
			behaviours.add(b);
			b.onAdopt(this);
		}
		toAdopt.clear();
		it = toAbandon.iterator();
		while (it.hasNext()) {
			Behaviour b = it.next();
			behaviours.remove(b);
			b.onAbandon(this);
		}
		toAbandon.clear();
	}

	public void render(Graphics g) {
		/*
		 * g.setColor(Color.red); g.drawRect(x, y, 1, 1); float off = 0;
		 * Iterator<Behaviour> it = behaviours.iterator(); while (it.hasNext())
		 * { g.drawString(it.next().name(), x, y + off); off += 11; }
		 */
	}

	public VState getState() {
		return state;
	}

	public List<Behaviour> getBehaviours() {
		return behaviours;
	}

	public void step_towards(Point point) {
		direction = new Point(x, y).directionTo(point);
		step_foward();		
	}//TODO: this is where path finding will be implemented

	public void setState(VState state) {
		this.state = state;
		
	}

	public void setDirection(float f) {
		this.direction = f;
	}

	public void setCollecting(Produce collecting) {
		this.collecting = collecting;
	}

	public Produce getCollecting() {
		return collecting;
	}
}
