package kernel;

import java.util.Set;

import model.Discoverable;

public class DiscoveryManager {

	private static Set<String> discoveries;
	
	public static void addDiscovery(Discoverable subject) {
		discoveries.add(subject.getIdentifier());
	}

}
