package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import kernel.Direction;
import kernel.Entity;
import kernel.Point;
import kernel.managers.WorldManager;
import model.VState;
import model.Villager;
import model.nature.Land;
import model.nature.Produce;

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

	private static final float PI = (float)Math.PI;
	
	SpriteAnimation stateAnim = null;

	public VillagerRenderer(Entity v) {
		super(v);
		sprite = SpriteLoader.get(Sprite.Villager);
		try {
			villager = (Villager) v;
		} catch (Exception e) {
			throw new RuntimeException(
					"Passing incorrect entity to renderer.\nEntity : "
							+ v.getClass().getSimpleName() + "\nRenderer : "
							+ getClass().getSimpleName());
		}
		spriteMap = initSpriteMap();
		subRenderers = new LinkedList<>();
		villager.getBus().register(this);
		differedInstructions = Lists.newLinkedList();
		imageSpeed = .6f;
	}

	private static HashMap<model.VState, Integer> initSpriteMap() {
		if (defaultSpriteMap == null) {
			defaultSpriteMap = new HashMap<>();
			int idle = Sprite.Villager;
			int walking = Sprite.Villager;
			int working = Sprite.Villager;
			int collecting = Sprite.Villager;

			defaultSpriteMap.put(VState.IDLE, idle);
			defaultSpriteMap.put(VState.COLLECTING, collecting);
			defaultSpriteMap.put(VState.GOING, walking);
			defaultSpriteMap.put(VState.WANDERING, walking);
			defaultSpriteMap.put(VState.LABOURING, working);
		}
		return defaultSpriteMap;
	}

	@Override
	public void render(final Graphics g) {
		for (Runnable r : differedInstructions) {
			r.run();
		}
		differedInstructions.clear();
		updateSprite();

		int looking = Direction.RIGHT;
		float d = villager.getDirection();
		if (d >= 5*PI/4 && d < 7*PI/4)
			looking = Direction.DOWN;
		if (d >= 3*PI/4 && d < 5*PI/4)
			looking = Direction.LEFT;
		if (d >= PI/4 && d < 3*PI/4)
			looking = Direction.UP;
		
		super.render(g,looking);
		g.drawString(""+villager.getDirection(), villager.getX(), villager.getY());
		for (final Renderer r : subRenderers) {
			differedInstructions.add(new Runnable() {
				@Override
				public void run() {
					r.render(g);
				}
			});

		}
	}

	private void updateSprite() {
		VState vstate = villager.getState();
		Integer s = spriteMap.get(vstate);
		if (s != null) {
			sprite = SpriteLoader.get(s);
		}

		/*if (villagerIsInDeepWater()) {
			sprite = sprite.getSubSprite(0, 0, sprite.getWidth(),
					sprite.getHeight() / 2);
		}*/
	}

	private boolean villagerIsInDeepWater() {
		Land land = WorldManager.get().getLandUnder(villager);
		if (land == null) {
			return false;
		}
		Land.Type on = land.getType();
		if (on == Land.Type.SEA) {// TODO: make this a property
			Collection<Land> shores = WorldManager.get().getLandsAround(
					villager, .5f);
			for (Land l : shores) {
				if (l.getType() != Land.Type.SEA) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Subscribe
	public void on(Villager.StateChangeEvent e) {

		if (stateAnim != null) {
			stateAnim.end();
			stateAnim = null;
		}

		switch (villager.getState()) {
		case COLLECTING:
			differedInstructions.add(new Runnable() {
				@Override
				public void run() {
					Point loc = villager.getLocation().withOffset(0,
							-sprite.getHeight());
					Produce prod = villager.getCollecting();
					if (prod != null) {
						subRenderers.add(new ProduceRenderer(loc, prod,
								subRenderers));
					}

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
	}

}
