package view.animation;

import view.SpriteRenderer;

public abstract class SpriteAnimation implements Runnable {
	protected SpriteRenderer parent;

	public SpriteAnimation(SpriteRenderer parent) {
		this.parent = parent;
	}

	public void end() {
	}

}
