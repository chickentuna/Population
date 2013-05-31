package view.debug;

import java.util.List;

import kernel.Point;
import kernel.managers.WorldManager;
import model.Villager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import view.Renderer;

public class DebugWorldVillagerRenderer implements Renderer {

	public final static int IDLE = 0;
	public final static int WALKING = 1;
	public final static int LABOUR = 2;
	public final static int COLLECT = 3;

	private Villager villager;

	// private int spriteIndex;

	public DebugWorldVillagerRenderer(Villager v) {
		villager = v;
	}

	// For rendering, get Images from View? No, views arnt just about sprites.
	// How to get sprites?
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
		case GOING:
			break;
		default:
			break;
		}
		g.setColor(c);
		g.drawRect(villager.getX(), villager.getY(), 1, 1);
		List<Point> points = WorldManager.get().getLocationsAround(villager, 0);
		for (Point p : points) {
			g.setColor(Color.black);
			g.drawLine(p.getX(), p.getY(), villager.getX(), villager.getY());
			g.drawOval(p.getX() - 2, p.getY() - 2, 4, 4);
		}
		g.drawString("" + points.size(), villager.getX(), villager.getY());
	}


}
