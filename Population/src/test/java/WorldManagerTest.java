

import static org.junit.Assert.assertEquals;
import kernel.managers.WorldManager;

import org.junit.Test;

public class WorldManagerTest {

	@Test
	public void getNextValueTest() {
		WorldManager wm = WorldManager.get();

		float dist = 1;

		assertEquals("1", 0, wm.getNextValue(-1, dist), 0);
		assertEquals("1", 1, wm.getNextValue(0, dist), 0);

		dist = .5f;
		assertEquals(".5", 0, wm.getNextValue(-.5f, dist), 0);
		assertEquals(".5", .5f, wm.getNextValue(0, dist), 0);

		dist = 2.5f;
		assertEquals("2.5", -2, wm.getNextValue(-2.5f, dist), 0);
		assertEquals("2.5", 2, wm.getNextValue(1, dist), 0);
		assertEquals("2.5", 2.5, wm.getNextValue(2, dist), 0);

	}

}
