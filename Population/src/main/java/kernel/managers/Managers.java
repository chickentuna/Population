package kernel.managers;

import io.GameBus;
import io.graphics.SpriteLoader;

public class Managers {

	public static void cleanInit() {
		GameBus.get();
		SpriteLoader.init();
		
		WorldManager.get();
		DecisionManager.get();
		RessourceManager.get();
		DiscoveryManager.get();
		EntityManager.get();

	}

}
