package view;

import java.util.HashMap;

import kernel.Class2ClassMap;
import kernel.Entity;
import kernel.events.EntityRenameEvent;
import kernel.managers.RessourceManager;
import kernel.managers.WorldManager;
import model.Villager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.google.common.eventbus.Subscribe;

public class DebugView implements View {

	Renderer worldRenderer;
	HashMap<Entity, Renderer> entities;
	Class2ClassMap<Entity, Renderer> renderMap;

	public DebugView() {
		worldRenderer = new WorldRenderer(WorldManager.get().getWorld());
		entities = new HashMap<>();
		renderMap = createRenderMap();
	}

	private Class2ClassMap<Entity, Renderer> createRenderMap() {
		Class2ClassMap<Entity, Renderer> map = new Class2ClassMap<>();
		map.put(Villager.class, VillagerRenderer.class);
		return map;
	}

	public void render(Graphics g) {
		worldRenderer.render(g);
		renderVillagers(g); //TODO: give render depth to entities ?
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

	@Subscribe
	public void on(EntityRenameEvent event) {
		Entity e = event.getEntity();
		switch (event.getType()) {
		case EntityRenameEvent.SPAWN:
			try {
				Class<? extends Renderer> clazz = renderMap.get(e.getClass());
				Renderer r;
				if (clazz != null) {
					r = clazz.getConstructor(Entity.class).newInstance(e);
				} else {
					r = new SpriteRenderer(e);
				}
				entities.put(e, r);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			break;
		case EntityRenameEvent.UNSPAWN:
			entities.remove(e);
			break;
		default:
			break;
		}
	}
}
