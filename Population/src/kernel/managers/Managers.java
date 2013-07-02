package kernel.managers;

import io.GameBus;
import io.graphics.SpriteLoader;

public class Managers {

	public static void cleanInit() {
		GameBus.get();
		SpriteLoader.init();
		
		WorldManager.get("1\n5\n5\n"
				+ "22220\n"
				+ "22220\n"
				+ "22200\n"
				+ "22220\n"
				+ "22220\n");
		DecisionManager.get();
		RessourceManager.get();
		DiscoveryManager.get();
		EntityManager.get();

	}

}
