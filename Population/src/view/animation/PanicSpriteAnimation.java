package view.animation;

import io.Engine;
import view.SpriteRenderer;

public class PanicSpriteAnimation extends SpriteAnimation {

	private int tick = 0;
	private float startScale;
	private int targetTicks;
	private int tickFreq;

	public PanicSpriteAnimation(SpriteRenderer parent, int freq, int duration) {
		super(parent);

		tickFreq = Engine.TARGET_FPS / freq;
		targetTicks = duration * Engine.TARGET_FPS;
		startScale = parent.getXScale();

	}

	public PanicSpriteAnimation(SpriteRenderer renderer, int freq) {
		this (renderer, freq, -1);
	}

	@Override
	public void run() {
		tick++;
		if (tick % tickFreq == 0) {
			parent.setXScale(-parent.getXScale());
		}
		if (targetTicks >= 0 && tick >= targetTicks) {
			end();
		}

	}

	@Override
	public void end() {
		parent.setXScale(startScale);
		parent.removeAnimation(this);
	}

}
