package pop;

public class Villager extends Entity {
	
	private String name,surname;
	private Job job;
	private ResidentialBuilding home;
	private Sex sex = Sex.MALE;
	private State state = State.IDLE;
	
	public enum Sex { MALE, FEMALE }
	public enum State { IDLE, WORKING, LOITERING, CARRYING, GOINGTO, BUILDING }
	
	
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
