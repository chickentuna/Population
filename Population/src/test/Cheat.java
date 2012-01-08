package test;

import pop.Entity;
import pop.EntityManager;
import pop.Villager;
import pop.Villager.Sex;

public class Cheat {
	public static void perform(String cheat) {
		if (cheat.equals("spawnvillager")) {
			Villager v = (Villager) EntityManager
					.spawn(new Villager(310f, 240f));
			if ((int) (Math.random() * 2) == 0)
				v.setSex(Sex.FEMALE);
			
		} else if (cheat.equals("babyboom")) {
			for (int i = 0; i < 100; i++) {
				Villager v = (Villager) EntityManager.spawn(new Villager(310f,
						240f));
				if ((int) (Math.random() * 2) == 0)
					v.setSex(Sex.FEMALE);
			}
			
		} else if (cheat.equals("tenpeople")) {
			for (int i = 0; i < 10; i++) {
				Villager v = (Villager) EntityManager.spawn(new Villager(310f,
						240f));
				if ((int) (Math.random() * 2) == 0)
					v.setSex(Sex.FEMALE);
			}
		} else if (cheat.equals("blackplague")) {
			for (Entity v : EntityManager.get(Villager.class)) {
				EntityManager.unspawn(v);
			}
		}
			

	}
}
