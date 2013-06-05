package view;

import io.Engine;
import io.graphics.Sprite;
import io.graphics.SpriteLoader;

import java.util.List;

import kernel.Point;
import model.nature.Produce;

import org.newdawn.slick.Graphics;

public class ProduceRenderer extends SpriteRenderer {

	List<? extends Renderer> container;
	private int tick = 0;
	private int targetTick = 2 * Engine.TARGET_FPS;

	public ProduceRenderer(Point location, Produce produce, List<? extends Renderer> container) {
		super(location);
		this.container = container;

		int spriteIndex;
		switch (produce) {// TODO: Make (static) HashMaps for sprites
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

	@Override
	public void render(Graphics g) {
		super.render(g);
		tick++;
		if (tick >= targetTick) {
			container.remove(this);
		}
	}
}