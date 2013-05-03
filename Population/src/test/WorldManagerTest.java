package test;

import kernel.managers.WorldManager;

import org.junit.Test;

public class WorldManagerTest {

	@Test
	public void test() {
		float x = -10;
		int s = 100;
		System.out.println(/* (int) */(x / (float) s));
		System.out.println(WorldManager.get().getLandAt(-10, 0));

	}

}
