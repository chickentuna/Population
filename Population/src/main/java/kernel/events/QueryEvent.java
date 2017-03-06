package kernel.events;

public class QueryEvent {

	String id;
	
	public QueryEvent(String s) {
		this.id = s;
	}

	public Object getId() {
		return id;
	}

}
