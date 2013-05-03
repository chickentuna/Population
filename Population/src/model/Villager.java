package model;

import static model.VState.IDLE;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import kernel.Entity;
import kernel.Progress;
import kernel.managers.WorldManager;
import model.nature.Land;
import model.nature.Produce;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import technology.Building;

public class Villager extends Entity {

	public static final int WALK_SPEED = 1;

	private Set<Behaviour> behaviours;
	private Set<Behaviour> toAdopt;
	private Set<Behaviour> toAbandon;

	// Behaviour vars
	protected VState state = IDLE;
	protected float direction = 0;
	protected HashMap<Behaviour, Progress> progress;
	protected Building building = null;
	protected Produce collecting = null;

	public Villager(int x, int y) {
		super((float) x, (float) y);
		behaviours = new HashSet<Behaviour>();
		toAbandon = new HashSet<Behaviour>();
		toAdopt = new HashSet<Behaviour>();
		progress = new HashMap<>();

	}

	public void adoptBehaviour(Behaviour b) {
		toAdopt.add(b);
	}

	public void abandonBehaviour(Behaviour behaviour) {
		toAbandon.add(behaviour);
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
		refreshBehaviours();
	}

	private void refreshBehaviours() {

		Iterator<Behaviour> it = toAbandon.iterator();
		while (it.hasNext()) {
			Behaviour b = it.next();
			behaviours.remove(b);
			b.onAbandon();
		}
		toAbandon.clear();
		it = toAdopt.iterator();
		while (it.hasNext()) {
			Behaviour b = it.next();
			behaviours.add(b);
			b.onAdopt();
		}
		toAdopt.clear();

	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.drawRect(x, y, 1, 1);// TODO: debug info
	}

	public void setBuilding(Building b) {
		building = b;
	}

	public Progress getProgressFor(Behaviour behaviour) {
		if (!progress.containsKey(behaviour)) {
			progress.put(behaviour, null);
			return null;
		}
		return progress.get(behaviour);
	}

	public void setProgressFor(Behaviour b, int duration) {
		progress.put(b, new Progress(duration));
	}

	public void clearProgressFor(Behaviour b) {
		progress.remove(b);

	}

}
