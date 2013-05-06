package io;

import com.google.common.eventbus.EventBus;

public class GameBus  {
	private EventBus bus;
	private static GameBus self = null;
	
	private GameBus() {
		bus = new EventBus();
	}
	
	public static GameBus get() {
		if (self == null)
			self=new GameBus();
		return self;
	}

	public static void post(Object o) {
		get().bus.post(o);		
	}
	
	public static void register(Object e) {
		get().bus.register(e);		
	}
}
