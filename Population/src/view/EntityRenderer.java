package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import kernel.Entity;

import org.newdawn.slick.Graphics;

public class EntityRenderer implements Renderer {

	protected Entity entity;
	protected Sprite sprite = SpriteLoader.get(Sprite.Missing);

	public EntityRenderer(Entity e) {
		entity = e;
	}


	@Override
	public void render(Graphics g) {
		int x = (int) (entity.getX() - sprite.getWidth() / 2);
		int y = (int) (entity.getY() - sprite.getHeight() / 2);

		g.drawImage(sprite, x, y);

	}


}
