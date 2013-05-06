package kernel.managers;

import io.GameBus;

public class Managers {

	public static void cleanInit() {
		GameBus.get();
		WorldManager.get();
		DecisionManager.get();
		RessourceManager.get();
		DiscoveryManager.get();
		EntityManager.get();

	}

}
