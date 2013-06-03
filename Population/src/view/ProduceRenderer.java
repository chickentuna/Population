package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import kernel.Point;
import model.nature.Produce;

public class ProduceRenderer extends SpriteRenderer {

	public ProduceRenderer(Point location, Produce produce) {
		super(location);

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

}