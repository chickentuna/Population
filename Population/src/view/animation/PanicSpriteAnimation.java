package view.animation;

import view.SpriteRenderer;

public class PanicSpriteAnimation extends SpriteAnimation {

	private int tick = 0;
	private float startScale;

	public PanicSpriteAnimation(SpriteRenderer parent) {
		super(parent);
		startScale = parent.getXScale();
	}

	@Override
	public void run() {
		tick = (tick++) % 100;
		if (tick == 0) {
			parent.setXScale(-parent.getXScale());
		}

	}

	@Override
	public void end() {
		parent.setXScale(startScale);
	}

}
