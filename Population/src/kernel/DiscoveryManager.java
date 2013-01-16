package kernel;

import java.util.HashSet;
import model.Discoverable;

public class DiscoveryManager {

	private static HashSet<String> discoveries = new HashSet<>();
	
	public static void addDiscovery(Discoverable subject) {
		discoveries.add(subject.getIdentifier());
		
	}

}
