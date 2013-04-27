package kernel.managers;

public class Managers {

	public static void cleanInit() {
		WorldManager.get();
		DecisionManager.get();
		RessourceManager.get();
		DiscoveryManager.get();
		EntityManager.get();

	}

}
