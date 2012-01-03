package pop;

public class Villager extends Entity {
	
	static final int MAX = 10;
	
	private String name,surname;
	private Job job;
	private ResidentialBuilding home;
	private Sex sex = Sex.MALE;
	private State state = State.IDLE;
	private GoodMap carrying;
	
	public enum Sex { MALE, FEMALE }
	public enum State { IDLE, WORKING, LOITERING, CARRYING, GOINGTO, BUILDING }
	
	
	public Villager() {
		carrying = new GoodMap();
		state = State.IDLE;
	}
	
	public void update() {
		if (state.equals(State.CARRYING)) {
			//TODO: Find town centre
			
		}
		
	}
	
	//TODO: Transfer part of this method to GoodMap as "take"
	public void collect(GoodMap pile) {
		state = State.CARRYING;
		for (Good good : pile.keySet()) {
			int k = pile.get(good);
			if (carrying.getAmount()<MAX) {
				if (k>=MAX-carrying.getAmount()) {
					pile.put(good, k-MAX-carrying.getAmount());
					carrying.put(good, MAX-carrying.getAmount());
				} else {
					pile.put(good, 0);
					carrying.put(good, k);
				}
			}
		}
		
	}
	
	
	public Sex getSex() {
		return sex;
	}
	public Villager setSex(Sex sex) {
		this.sex = sex;
		return this;
	}
	
	public String getName() {
		return name;
	}
	public Villager setName(String name) {
		this.name = name;
		return this;
	}
	public String getSurname() {
		return surname;
	}
	public Villager setSurname(String surname) {
		this.surname = surname;
		return this;
	}
	public int getX() {
		return x;
	}
	public Villager setX(int x) {
		this.x = x;
		return this;
	}
	public int getY() {
		return y;
	}
	public Villager setY(int y) {
		this.y = y;
		return this;
	}
	public Job getJob() {
		return job;
	}
	public Villager setJob(Job job) {
		this.job = job;
		return this;
	}
	public ResidentialBuilding getHome() {
		return home;
	}
	public Villager setHome(ResidentialBuilding home) {
		this.home = home;
		return this;
	}
	public Villager setState(State state) {
		this.state = state;
		return this;
	}
	public State getState() {
		return state;
	}
	
}
