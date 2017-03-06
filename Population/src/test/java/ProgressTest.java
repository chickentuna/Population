

import static org.junit.Assert.assertEquals;
import kernel.Progress;

import org.junit.Test;

public class ProgressTest {

	@Test
	public void test() {
		long start = System.currentTimeMillis();
		long now;

		Progress p = new Progress(2);
		while (p.getPercentage() < 100) {
			now = System.currentTimeMillis();
			if (now - start == 1001) {
				assertEquals(50, p.getPercentage());
			}

		}
		assertEquals(100, p.getPercentage());
	}
}
