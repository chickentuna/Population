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

import view.animation.PanicSpriteAnimation;
import view.animation.SpriteAnimation;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

public class VillagerRenderer extends SpriteRenderer {

	private static HashMap<VState, Integer> defaultSpriteMap = null;

	private Villager villager;
	private HashMap<VState, Integer> spriteMap;
	private List<Renderer> subRenderers;
	private List<Runnable> differedInstructions;

	SpriteAnimation stateAnim;
	private VState previousState = VState.IDLE;
	
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
		differedInstructions = Lists.newLinkedList();
	}

	private static HashMap<model.VState, Integer> getSpriteMap() {

		if (defaultSpriteMap == null) {
			defaultSpriteMap = new HashMap<>();
			int idle = Sprite.Clefairy;
			int walking = Sprite.Clefairy;
			int working = Sprite.Clefairy;
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
		for (Runnable r : differedInstructions) {
			r.run();
		}
		differedInstructions.clear();
		
		updateSprite();

		super.render(g);
		for (Renderer r : subRenderers) {
			r.render(g);
		}
	}

	private void updateSprite() {
		VState vstate = villager.getState();
		Integer s = spriteMap.get(vstate);
		if (s != null) {
			sprite = SpriteLoader.get(s);
		}		
	}

	@Subscribe
	public void on(Villager.StateChangeEvent e) {
		
		stateAnim.end();
		
		switch (villager.getState()) {
		case COLLECTING:
			differedInstructions.add(new Runnable() {
				@Override
				public void run() {
					subRenderers.add(new ProduceRenderer(villager.getLocation().withOffset(0, -sprite.getHeight()), villager.getCollecting()));
					//TODO: How To Remove this ?
				}
			});

			break;
		case GOING:
			break;
		case IDLE:
			break;
		case LABOURING:
			stateAnim = new PanicSpriteAnimation(this, 4);
			spriteAnimations.add(stateAnim);
			break;
		default:
			break;
		}
		previousState  = villager.getState();
	}

}
