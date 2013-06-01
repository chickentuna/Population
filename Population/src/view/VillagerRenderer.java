package view;

import java.util.HashMap;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import kernel.Entity;
import kernel.Point;
import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import model.VState;
import model.Villager;
import model.behaviour.GoingBehaviour;

public class VillagerRenderer extends EntityRenderer {

	private Villager villager;
	private HashMap<VState, Integer> spriteMap;

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

	}

	private HashMap<model.VState, Integer> createSpriteMap() {
		HashMap<VState, Integer> res = new HashMap<>();
		int idle = Sprite.Clefairy;
		int walking = Sprite.Clefairy;
		int working = Sprite.Clefairy;
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
	}

}
