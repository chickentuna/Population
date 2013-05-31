package test;

import kernel.managers.WorldManager;
import model.Villager;

import org.junit.Test;

public class WorldManagerTest {

	@Test
	public void test() {

		System.out.println(WorldManager.get().getLocationsAround(new Villager(350, 350), 1));
	}

}
