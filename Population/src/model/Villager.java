package model;

import static model.VState.IDLE;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import kernel.Entity;
import kernel.Point;
import kernel.managers.WorldManager;
import model.behaviour.Behaviour;
import model.nature.Land;
import model.nature.Produce;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;

public class Villager extends Entity {

	public static final int WALK_SPEED = 1;

	private List<Behaviour> behaviours;
	private List<Behaviour> toAdopt;
	private List<Behaviour> toAbandon;

	private EventBus bus;
	
	private VState state = IDLE;
	private float direction = 0;
	private Produce collecting;

	public class ObtainProduceEvent {}
	
	
	
	public Villager(int x, int y) {
		super((float) x, (float) y);
		behaviours = new LinkedList<Behaviour>();
		toAbandon = new LinkedList<Behaviour>();
		toAdopt = new LinkedList<Behaviour>();
		bus = new EventBus();
	}
	
	public void adoptBehaviour(Behaviour b) {
		toAdopt.add(b);
	}

	public void abandonBehaviour(Behaviour behaviour) {
		toAbandon.add(behaviour);
	}

	//TODO: Place in Behaviours
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
		
		for (Behaviour b : behaviours) {
			b.execute(this);
		}
	}

	private void refreshBehaviours() {
        List<Runnable> todos = Lists.newArrayList();

        final Villager self = this;

        Iterator<Behaviour> it = toAdopt.iterator();
		while (it.hasNext()) {
            final Behaviour b = it.next();
            todos.add(new Runnable() {
                @Override
                public void run() {
                    behaviours.add(b);
                    b.onAdopt(self);
                    
                }
            });
		}
        toAdopt.clear();

        it = toAbandon.iterator();
		while (it.hasNext()) {
            final Behaviour b = it.next();
            todos.add(new Runnable() {
                @Override
                public void run() {
                    behaviours.remove(b);
                    b.onAbandon(self);
                }
            });
		}

        toAbandon.clear();

        for (Runnable action : todos) {
            action.run();
        }

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
		if (collecting != null) {
			bus.post(new ObtainProduceEvent());
		}
	}

	public Produce getCollecting() {
		return collecting;
	}

	public float getDirection() {
		return direction;
	}

	public EventBus getBus() {
		return bus;		
	}
}
