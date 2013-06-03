package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import kernel.Entity;
import model.VState;
import model.Villager;

import org.newdawn.slick.Graphics;

import com.google.common.eventbus.Subscribe;

public class VillagerRenderer extends SpriteRenderer {

	private static HashMap<VState, Integer> defaultSpriteMap = null;

	private Villager villager;
	private HashMap<VState, Integer> spriteMap;
	private List<Renderer> subRenderers;

	public VillagerRenderer(Entity v) {
		super(v);
		sprite = SpriteLoader.get(Sprite.Clefairy);
		try {
			villager = (Villager) v;
		} catch (Exception e) {
			throw new RuntimeException("Passing incorrect entity to renderer.\nEntity : " + v.getClass().getSimpleName() + "\nRenderer : " + getClass().getSimpleName());
		}
		spriteMap = getSpriteMap();
		subRenderers = new LinkedList<>();
		villager.getBus().register(this);
	}

	private static HashMap<model.VState, Integer> getSpriteMap() {

		if (defaultSpriteMap == null) {
			defaultSpriteMap = new HashMap<>();
			int idle = Sprite.Clefairy;
			int walking = Sprite.Clefairy;
			int working = Sprite.Crab;
			int collecting = Sprite.Clefairy;

			defaultSpriteMap.put(VState.IDLE, idle);
			defaultSpriteMap.put(VState.COLLECTING, collecting);
			defaultSpriteMap.put(VState.GOING, walking);
			defaultSpriteMap.put(VState.WANDERING, walking);
			defaultSpriteMap.put(VState.LABOURING, working);
		}

		return defaultSpriteMap;
	}

	@Override
	public void render(Graphics g) {

		Integer s = spriteMap.get(villager.getState());
		if (s != null) {
			sprite = SpriteLoader.get(s);
		}
		super.render(g);
		for (Renderer r : subRenderers) {
			r.render(g);
		}
	}

	@Subscribe
	public void on(Villager.ObtainProduceEvent e) {
		subRenderers.add(new ProduceRenderer(villager.getLocation().withOffset(0, -24), villager.getCollecting()));
	}

}
