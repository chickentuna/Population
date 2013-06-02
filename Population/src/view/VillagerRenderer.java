package view;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Graphics;

import com.google.common.eventbus.Subscribe;

import kernel.Entity;
import kernel.Point;
import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import model.VState;
import model.Villager;
import model.nature.Produce;

public class VillagerRenderer extends EntityRenderer {

	private class ProduceRenderer extends EntityRenderer {

		Point location; //TODO: EntityRender has no Entity but has Locatable?
		
		public ProduceRenderer(Point location, Produce produce) {
			super(null);
			
			this.location = location;
			
			int spriteIndex;
			switch (produce) {//TODO: Make HashMap
			case APPLE:
				spriteIndex = Sprite.Apple;
				break;
			case CRAB:
				spriteIndex = Sprite.Crab;
				break;
			case FISH:
				spriteIndex = Sprite.Fish;
				break;
			case LOG:
				spriteIndex = Sprite.Log;
				break;
			case ORE:
				spriteIndex = Sprite.Ore;
				break;
			case PLANKS:
				spriteIndex = Sprite.Plank;
				break;
			case STONE:
				spriteIndex = Sprite.Stone;
				break;
			case WHEAT:
				spriteIndex = Sprite.Wheat;
				break;
			default:
				spriteIndex = Sprite.Missing;
				break;
			}
			sprite = SpriteLoader.get(spriteIndex);
		}

	}

	private Villager villager;
	private HashMap<VState, Integer> spriteMap;
	private List<Renderer> subRenderers;

	public VillagerRenderer(Entity v) {
		super(v);
		sprite = SpriteLoader.get(Sprite.Clefairy);
		try {
			villager = (Villager) entity;
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
