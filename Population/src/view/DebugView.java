package view;

import java.util.HashMap;

import kernel.Entity;
import kernel.events.VillagerEvent;
import kernel.managers.RessourceManager;
import kernel.managers.WorldManager;
import model.Villager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.google.common.eventbus.Subscribe;

public class DebugView implements View {

	Renderer worldRenderer;
	HashMap<Entity, Renderer> entities;

	public DebugView() {
		worldRenderer = new WorldRenderer(WorldManager.get().getWorld());
		entities = new HashMap<>();
	}

	public void render(Graphics g) {
		worldRenderer.render(g);
		renderVillagers(g);
		renderGUI(g);
	}

	private void renderVillagers(Graphics g) {
		for (Renderer r : entities.values()) {
			r.render(g);
		}
	}

	private void renderGUI(Graphics g) {
		int food = RessourceManager.get().getFood();
		int res = RessourceManager.get().getRessource();
		int pop = RessourceManager.get().getPopulation();
		g.setColor(Color.green);
		g.drawString("food: " + food + " res: " + res + " pop: " + pop, 0, 400);
	}

	// TODO: make table that maps each entity to a renderer. using
	// EntityRenderer by default

	@Subscribe
	public void on(VillagerEvent e) {
		// TODO: Listen to Entity.spawn() instead
		Villager v = e.getVillager();
		switch (e.getType()) {
		case VillagerEvent.BIRTH:
			entities.put(v, new VillagerRenderer(v));
			break;
		case VillagerEvent.DEATH:
			entities.remove(v);
			break;
		default:
			break;
		}
	}
}
