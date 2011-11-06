package pop;

import java.util.HashMap;

public class GoodMap extends HashMap<Good,Integer> {

	private static final long serialVersionUID = -6106535498011123494L;
	
	public GoodMap() {
		super();
	}
	public GoodMap(Good good, int amount) {
		super();
		put(good,amount);
	}
	

}
