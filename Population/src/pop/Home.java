package pop;

public class Home extends ResidentialBuilding {
	
	public static final int CAPACITY = 5;
	
	public Home() {
		super(CAPACITY);
		type = Building.Type.HOME;
	}
	
}
