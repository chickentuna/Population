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

	private Villager villager;
	private HashMap<VState, Integer> spriteMap;
	// TODO: This HashMap should not be in every Renderer.
	private List<Renderer> subRenderers;

	public VillagerRenderer(Entity v) {
		super(v);
		sprite = SpriteLoader.get(Sprite.Clefairy);
		try {
			villager = (Villager) v;
		} catch (Exception e) {
			throw new RuntimeException(
					"Passing incorrect entity to renderer.\nEntity : "
							+ v.getClass().getSimpleName() + "\nRenderer : "
							+ getClass().getSimpleName());
		}
		spriteMap = createSpriteMap();
		subRenderers = new LinkedList<>();
		villager.getBus().register(this);
	}

	private HashMap<model.VState, Integer> createSpriteMap() {
		HashMap<VState, Integer> res = new HashMap<>();
		int idle = Sprite.Clefairy;
		int walking = Sprite.Clefairy;
		int working = Sprite.Crab;
		int collecting = Sprite.Clefairy;

		res.put(VState.IDLE, idle);
		res.put(VState.COLLECTING, collecting);
		res.put(VState.GOING, walking);
		res.put(VState.WANDERING, walking);
		res.put(VState.LABOURING, working);
		return res;
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
		subRenderers.add(new ProduceRenderer(villager.getLocation(), villager.getCollecting()));
	}

}
