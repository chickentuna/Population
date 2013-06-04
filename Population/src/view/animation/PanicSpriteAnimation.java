package view.animation;

import io.Engine;
import view.SpriteRenderer;

public class PanicSpriteAnimation extends SpriteAnimation {

	private int tick = 0;
	private float startScale;
	private int targetTicks;
	private int tickFreq;

	public PanicSpriteAnimation(SpriteRenderer parent, int duration, int freq) {
		super(parent);

		tickFreq = Engine.TARGET_FPS / freq;
		targetTicks = duration * Engine.TARGET_FPS;
		startScale = parent.getXScale();

	}

	@Override
	public void run() {
		tick++;
		if (tick % tickFreq == 0) {
			parent.setXScale(-parent.getXScale());
		}
		if (tick >= targetTicks) {
			end();
		}

	}

	@Override
	public void end() {
		parent.setXScale(startScale);
		parent.removeAnimation(this);
	}

}
