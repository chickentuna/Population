package view;

import kernel.Entity;
import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import model.Villager;

public class VillagerRenderer extends EntityRenderer {

	public final static int IDLE = 0;
	public final static int WALKING = 1;
	public final static int LABOUR = 2;
	public final static int COLLECT = 3;

	public VillagerRenderer(Entity v) {
		super(v);
		sprite = SpriteLoader.get(Sprite.Clefairy);
	}


}
