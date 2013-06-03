package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;
import kernel.Locatable;

import org.newdawn.slick.Graphics;

public class SpriteRenderer implements Renderer {

	protected Locatable location;
	protected Sprite sprite = SpriteLoader.get(Sprite.Missing);
	protected int xScale = 1;
	protected int yScale = 1;

	public SpriteRenderer(Locatable locatable) {
		location = locatable;
	}

	@Override
	public void render(Graphics g) {
		int x = (int) (location.getX() - sprite.getWidth() / 2);
		int y = (int) (location.getY() - sprite.getHeight() / 2);

		if (xScale == 1 && yScale == 1) {
			g.drawImage(sprite, x, y);
		} else {
			float w = sprite.getWidth() * xScale;
			float xd1 = x;
			float xd2 = x + w;
			if (xd2 < xd1) {
				xd2 += Math.abs(w);
				xd1 += Math.abs(w);
			}
			float h = sprite.getHeight() * yScale;
			float yd1 = y;
			float yd2 = y + h;
			if (yd2 < yd1) {
				yd2 += Math.abs(h);
				yd1 += Math.abs(h);
			}

			g.drawImage(sprite, xd1, yd1, xd2, yd2, 0, 0, sprite.getWidth(), sprite.getHeight());
		}

	}

}
