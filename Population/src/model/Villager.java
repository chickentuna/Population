package model;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;

import kernel.Entity;


public class Villager extends Entity {

	public static final int speed = 1;
	
	private List<Behaviour> behaviour;
	protected State state = State.IDLE;
	private float direction = 0;
	
	protected enum State {
		IDLE,
		WANDERING;
	}
	
	public Villager(int x, int y) {
		super((float)x,(float)y);
		behaviour = new LinkedList<Behaviour>();
		behaviour.add(new Behaviour() {
			
			public void execute(Villager self) {
				if (self.state == Villager.State.IDLE) {
					double percent = Math.random()*100;
					if (percent < 4) {
						self.state = Villager.State.WANDERING;
						direction = (float) Math.random()*360;
					}
				} else if (self.state == Villager.State.WANDERING) {
					double percent = Math.random()*100;
					if (percent < 5) {
						self.state = Villager.State.IDLE;
					}
					self.step_foward();
				}
			}
		});
		
	}
	
	private void step_foward() {
		x+=speed*Math.cos(direction);
		y-=speed*Math.sin(direction);
	}
	
	public void update() {
		Iterator<Behaviour> it = behaviour.iterator();
		while (it.hasNext()) { 
			it.next().execute(this);
		}
	}
	
	public void render(Graphics g) {
		g.drawRect(x, y, 1, 1);
	}
	
}
