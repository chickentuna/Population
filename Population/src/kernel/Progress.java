package kernel;

public class Progress {
	long start;
	long delta;
	boolean done = false;

	public Progress(int seconds) {
		start = System.currentTimeMillis();
		delta = seconds * 1000;
	}

	public int getPercentage() {
		if (done)
			return 100;
		long now = System.currentTimeMillis();

		int p = (int) (100 * ((now - start) / (float) delta));

		if (p >= 100) {
			p = 100;
			done = true;
		}
		return p;
	}
}
