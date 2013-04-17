package kernel.managers;

import java.util.HashSet;

import kernel.events.QueryEvent;
import model.Discoverable;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class DiscoveryManager {

	private HashSet<String> discoveries;
	private EventBus bus;

	private static DiscoveryManager self = null;

	public static DiscoveryManager get() {
		if (self == null) {
			self = new DiscoveryManager();
		}
		return self;
	}

	public DiscoveryManager(String mode) {
		this();
	}

	public DiscoveryManager() {
		discoveries = new HashSet<>();
		bus = new EventBus();
	}

	public void addDiscovery(Discoverable subject) {
		String s = subject.getIdentifier();

		if (!discoveries.contains(s)) {
			discoveries.add(s);

			bus.post(new QueryEvent(s));

		}

	}

	@Subscribe
	public void on(QueryEvent e) {

	}

	public EventBus getQueryEventBus() {
		return bus;
	}

}
