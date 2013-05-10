package view;

import java.util.Iterator;

import kernel.events.VillagerEvent;
import model.Villager;
import model.behaviour.Behaviour;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class VillagerRenderer implements Renderer {

	public final static int IDLE = 0;
	public final static int WALKING = 1;
	public final static int LABOUR = 2;
	public final static int COLLECT = 3;
	
	private Villager villager;
	//private int spriteIndex;

	public VillagerRenderer(Villager v) {
		villager = v;
	}

	// For rendering, get Images from View? No, views arnt just about sprites. How to get sprites?
	@Override
	public void render(Graphics g) {
		Color c = Color.red;
		switch (villager.getState()) {
		case COLLECTING:
			c = new Color(.5f, .5f, 1f);
			break;
		case IDLE:
			break;
		case LABOURING:
			c = Color.orange;
			break;
		case WANDERING:
			break;
		default:
			break;
		}
		g.setColor(c);
		g.drawRect(villager.getX(), villager.getY(), 1, 1);
		float off = 0;
		Iterator<Behaviour> it = villager.getBehaviours().iterator();
		/*while (it.hasNext()) {
			g.drawString(it.next().name(), villager.getX(), villager.getY()
					+ off);
			off += 11;
		}*/

	}

	public void on(VillagerEvent e) {
		switch (e.getType()) {
		default:
			break;
		}
	}

}
