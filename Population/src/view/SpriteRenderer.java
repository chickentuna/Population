package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import kernel.Locatable;

import org.newdawn.slick.Graphics;

public class SpriteRenderer implements Renderer {

	protected Locatable location;
	protected Sprite sprite = SpriteLoader.get(Sprite.Missing);

	public SpriteRenderer(Locatable locatable) {
		location = locatable;
	}


	@Override
	public void render(Graphics g) {
		int x = (int) (location.getX() - sprite.getWidth() / 2);
		int y = (int) (location.getY() - sprite.getHeight() / 2);

		g.drawImage(sprite, x, y);

	}


}
