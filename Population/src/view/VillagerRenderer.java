package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import model.Villager;

import org.newdawn.slick.Graphics;

public class VillagerRenderer implements Renderer {

	public final static int IDLE = 0;
	public final static int WALKING = 1;
	public final static int LABOUR = 2;
	public final static int COLLECT = 3;

	private Villager villager;

	private int spriteIndex = Sprite.Apple;
	private Sprite sprite = SpriteLoader.get(spriteIndex);

	public VillagerRenderer(Villager v) {
		villager = v;
	}


	@Override
	public void render(Graphics g) {
		g.drawImage(sprite, (int) villager.getX(), (int) villager.getY());
	}


}
