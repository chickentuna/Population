package kernel.managers;

public class RessourceManager {
	private static final int BASE_FOOD = 20;
	private static final int BASE_RESSOURCE = 20;
	
	private int food;
	private int ressource;
	
	private static RessourceManager self = null;

	public static RessourceManager get() {
		if (self == null) {
			self = new RessourceManager();
		}
		return self;
	}

	public RessourceManager() {
		food = BASE_FOOD;
		ressource = BASE_RESSOURCE;
	}

	public int getFood() {
		return food;
	}

	public int getRessource() {
		return ressource;
	}
	
}
