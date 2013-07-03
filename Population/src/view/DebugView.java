package view;

import java.util.HashMap;

import kernel.Class2ClassMap;
import kernel.Entity;
import kernel.events.EntityRenameEvent;
import kernel.managers.RessourceManager;
import kernel.managers.WorldManager;
import model.Villager;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.google.common.eventbus.Subscribe;

public final class DebugView implements View {

	Renderer worldRenderer;
	HashMap<Entity, Renderer> entities;
	Class2ClassMap<Entity, Renderer> renderMap;
	Input userInput;
	
	int xTranslation = 0;
	int yTranslation = 0;
	int containerHeight;
	int containerWidth;
	int translationSpeed = 5;
	

	public DebugView(GameContainer container) {
		worldRenderer = new WorldRenderer(WorldManager.get().getWorld());
		entities = new HashMap<>();
		renderMap = createRenderMap();
		this.userInput = container.getInput();
		containerHeight = container.getHeight();
		containerWidth = container.getWidth();
	}

	private Class2ClassMap<Entity, Renderer> createRenderMap() {
		Class2ClassMap<Entity, Renderer> map = new Class2ClassMap<>();
		map.put(Villager.class, VillagerRenderer.class);
		return map;
	}

	@Override
	public void render(Graphics g) {
		if (userInput.isKeyDown(Keyboard.KEY_DOWN) && yTranslation >=  containerHeight - WorldManager.get().getHeight()) {
			yTranslation -= translationSpeed;
		}
		if (userInput.isKeyDown(Keyboard.KEY_UP) && yTranslation < 0) {
			yTranslation += translationSpeed;
		}
		if (userInput.isKeyDown(Keyboard.KEY_RIGHT) && xTranslation >= containerWidth - WorldManager.get().getWidth()) {
			xTranslation -= translationSpeed;
		}
		if (userInput.isKeyDown(Keyboard.KEY_LEFT) && xTranslation < 0) {
			xTranslation += translationSpeed;
		}		
		
		g.translate(xTranslation, yTranslation);
		worldRenderer.render(g);
		renderVillagers(g); // TODO: give render depth to entities ?
		g.translate(-xTranslation, -yTranslation);
		
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
		g.drawString("food: " + food + " res: " + res + " pop: " + pop, 0, 0);
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
