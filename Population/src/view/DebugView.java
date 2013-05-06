package view;

import java.util.HashMap;
import java.util.Iterator;

import kernel.events.VillagerEvent;
import kernel.managers.RessourceManager;
import kernel.managers.WorldManager;
import model.Villager;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.google.common.eventbus.Subscribe;

public class DebugView implements View {

	Renderer worldRenderer;
	HashMap<Villager, VillagerRenderer> villagers;

	public DebugView() {
		worldRenderer = new WorldRenderer(WorldManager.get().getWorld());
		villagers = new HashMap<>();
	}

	public void render(Graphics g) {
		worldRenderer.render(g);
		renderVillagers(g);
		renderGUI(g);
	}

	private void renderVillagers(Graphics g) {
		Iterator<VillagerRenderer> it = villagers.values().iterator();
		while (it.hasNext()) {
			it.next().render(g);
		}		
	}

	private void renderGUI(Graphics g) {
		int food = RessourceManager.get().getFood();
		int res = RessourceManager.get().getRessource();
		int pop = RessourceManager.get().getPopulation();
		g.setColor(Color.green);
		g.drawString("food: " + food + " res: " + res+ "pop: "+pop, 0, 400);
	}

	@Subscribe
	public void on(VillagerEvent e) {

		Villager v = e.getVillager();
		switch (e.getType()) {
		case VillagerEvent.BIRTH:
			villagers.put(v, new VillagerRenderer(v));
			break;
		case VillagerEvent.DEATH:
			villagers.remove(v);
			break;
		default:
			villagers.get(v).on(e);
			break;
		}
	}
}
