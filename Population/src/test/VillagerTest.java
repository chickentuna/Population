package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import kernel.EntityManager;
import kernel.WorldManager;
import model.Discoverable;
import model.Villager;

import org.junit.Before;
import org.junit.Test;

public class VillagerTest {

	private Villager v;
	
	@Before
	public void setUp() {
		EntityManager.init();
		WorldManager.init("Debug");
		
		v = new Villager(0,0);
		EntityManager.spawn(v);
	}
	
	@Test
	public void testGetSurroundings() {
		
		LinkedList<Discoverable> surroundings = v.getSurroundings();
		assertEquals("Not enough lands are visible to villager",6,surroundings.size());
		
	}

}
