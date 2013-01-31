package kernel.managers;

public class Managers {

	public static void cleanInit() {
		WorldManager.get();
		RessourceManager.get();
		DiscoveryManager.get();
		EntityManager.get();
		
		
	}

}
