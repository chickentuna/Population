package pop;

public class GoodPile extends Entity {

	private GoodMap content;
	
	public GoodPile(GoodMap content) {
		super(0,0);
		this.content = content;
	}

	public boolean isEmpty() {
		return content.isEmpty();
	}
	
	
	public GoodMap getContent() {
		return content;
	}

	public void setContent(GoodMap content) {
		this.content = content;
	}

	public static GoodPile dump(GoodMap content) {
		return new GoodPile(content);
		
	}

}
