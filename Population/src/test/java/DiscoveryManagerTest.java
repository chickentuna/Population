

import static org.junit.Assert.assertTrue;
import kernel.events.QueryEvent;
import kernel.managers.DiscoveryManager;
import model.Discoverable;
import model.nature.Land;

import org.junit.Test;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class DiscoveryManagerTest {

	@Test
	public void addDicoveryMultipleTest() {
		DiscoveryManager m = new DiscoveryManager();
		
		EventBus bus = m.getQueryEventBus();
		EventTester tester = new EventTester() {
			int counter = 0;
			
			@Subscribe
			public void on(QueryEvent e) {
				if (e.getId().equals("PLAIN"))
					counter++;
			}
			
			@Override
			public boolean valid() {
				return counter==1;
			}
		};
		
		bus.register(tester);
		
		Discoverable d = new Discoverable() {
			@Override
			public String getIdentifier() {
				return "PLAIN";
			}
		};
		DiscoveryManager.get().addDiscovery(d);
		DiscoveryManager.get().addDiscovery(d);
		DiscoveryManager.get().addDiscovery(d);
		assertTrue(tester.valid());
		System.err.println(tester.valid());
	}
	
	
	
	@Test
	public void addDicoveryLandTest() {
		Land l = new Land(Land.Type.PLAIN);
		DiscoveryManager.get().addDiscovery(l);
		
	}

}
