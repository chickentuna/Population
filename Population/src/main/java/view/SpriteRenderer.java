package view;

import io.graphics.Sprite;
import io.graphics.SpriteLoader;

import java.awt.Rectangle;
import java.util.LinkedList;
import java.util.List;

import kernel.Locatable;

import org.newdawn.slick.Graphics;

import view.animation.SpriteAnimation;

import com.google.common.collect.Lists;

public class SpriteRenderer implements Renderer {

	protected Locatable location;
	protected Sprite sprite = SpriteLoader.get(Sprite.Missing);
	protected float xScale = 1;
	protected float yScale = 1;
	protected List<SpriteAnimation> spriteAnimations;
	protected float imageNumber = 0;
	protected float imageSpeed = 1;
	protected Rectangle bounds;

	public SpriteRenderer(Locatable locatable) {
		location = locatable;
		spriteAnimations = new LinkedList<>();
		
	}

	@Override
	public void render(Graphics g) {
		render(g, 0);
	}

	public void render(Graphics g, int direction) {
		performAnimations();

		int sw;
		int sh;
		int col;
		int sy = 0;

		if (sprite.isCharacter()) {
			sw = sprite.getWidth() / Sprite.CHAR_SPRITE_COLS;
			sh = sprite.getHeight() / Sprite.CHAR_SPRITE_ROWS;
			sy = sh * direction;
			if ((int) imageNumber == Sprite.CHAR_SPRITE_COLS)
				col = sw;
			else
				col = ((int) imageNumber) * sw;
		} else {
			sw = sprite.getWidth() / sprite.getImageCount();
			sh = sprite.getHeight();
			col = ((int) imageNumber) * sw;
		}
		Sprite toDraw = sprite.getSubSprite(col, sy, sw, sh);

		int x = (int) (location.getX() - toDraw.getWidth() / 2);
		int y = (int) (location.getY() - toDraw.getHeight() / 2);

		if (xScale == 1 && yScale == 1) {
			if (bounds!=null) {
				toDraw = toDraw.getSubSprite(bounds.x, bounds.y, bounds.width, bounds.height);
			}
			g.drawImage(toDraw, x, y);
		} else {
			float w = toDraw.getWidth() * xScale;
			float xd1 = x;
			float xd2 = x + w;
			if (xd2 < xd1) {
				xd2 += Math.abs(w);
				xd1 += Math.abs(w);
			}
			float h = toDraw.getHeight() * yScale;
			float yd1 = y;
			float yd2 = y + h;
			if (yd2 < yd1) {
				yd2 += Math.abs(h);
				yd1 += Math.abs(h);
			}
			
			g.drawImage(toDraw, xd1, yd1, xd2, yd2, 0, 0, toDraw.getWidth(), toDraw.getHeight());
		}
		imageNumber += imageSpeed;
		if (imageNumber >= sprite.getImageCount()) {
			imageNumber = 0;
		}
	}

	private void performAnimations() {
		LinkedList<Runnable> todo = Lists.newLinkedList();

		for (SpriteAnimation sa : spriteAnimations) {
			todo.add(sa);
		}
		for (Runnable r : todo) {
			r.run();
		}

	}

	public float getXScale() {
		return xScale;
	}

	public void setXScale(float scale) {
		xScale = scale;
	}

	public float getYScale() {
		return yScale;
	}

	public void setYScale(float scale) {
		yScale = scale;
	}

	public void removeAnimation(SpriteAnimation spriteAnimation) {
		spriteAnimations.remove(spriteAnimation);
	}

}
