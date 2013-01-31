package kernel.managers;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import kernel.Query;
import kernel.events.QueryEvent;

import com.google.common.eventbus.EventBus;

import model.Discoverable;

public class DiscoveryManager {

	private HashSet<String> discoveries;
	private LinkedList<Method> queries;
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
		queries = new LinkedList<>();
		bus = new EventBus();

		Method[] methods = DiscoveryManager.class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			if (m.isAnnotationPresent(Query.class)) {
				queries.add(m);
			}
		}

	}

	public void addDiscovery(Discoverable subject) {
		String s = subject.getIdentifier();

		if (!discoveries.contains(s)) {
			discoveries.add(s);

			bus.post(new QueryEvent(s));

			try {
				raiseQueries(s);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	// TODO: Destroy annotation system, use bus instead
	private void raiseQueries(String s) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Iterator<Method> it = queries.iterator();
		while (it.hasNext()) {
			Method toCall = it.next();
			if (isQueryOf(toCall, s)) {
				toCall.invoke(this);
			}
		}

	}

	private boolean isQueryOf(Method toCall, String s) {
		return (toCall.getAnnotation(Query.class).value().equals(s));
	}

	@Query("PLAIN")
	public void plainQuery() {
		
	}

	public EventBus getQueryEventBus() {
		return bus;
	}

}
