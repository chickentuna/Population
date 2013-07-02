package kernel.managers;

import io.GameBus;
import io.graphics.SpriteLoader;

public class Managers {

	public static void cleanInit() {
		GameBus.get();
		SpriteLoader.init();
		
		WorldManager.get("1\n3\n2\n"
				
				+ "220\n"
				+ "200\n"
				/*+ "220\n"*/);
		DecisionManager.get();
		RessourceManager.get();
		DiscoveryManager.get();
		EntityManager.get();

	}

}
