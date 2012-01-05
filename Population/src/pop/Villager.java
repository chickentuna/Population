package pop;

import java.awt.Point;
import java.lang.Math;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Villager extends Entity {
	
	static final int MAX = 10;
	
	private String name,surname;
	private Job job;
	private Point destination = new Point();
	private ResidentialBuilding home;
	private Sex sex = Sex.MALE;
	private State state = State.IDLE;
	private GoodMap carrying;
	
	public enum Sex { MALE, FEMALE }
	public enum State { IDLE, WORKING, LOITERING, CARRYING, CARRYINGTO, GOINGTO, BUILDING }
	
	
	public Villager(float x, float y) {
		super(x,y);
		carrying = new GoodMap();
		state = State.IDLE;
	}
	
	public void update() {
		if (home==null) {
			home = EntityManager.getFreeHome();
			if (home!=null)
				home.addOccupant(this);
		}
		if (state==State.IDLE && job==null) {
			destination.x=(int) (x+Math.random()*200-100);
			destination.y=(int) (y+Math.random()*200-100);
			state=State.LOITERING;
		}
		if (state==State.LOITERING) {
			goTo(destination);
		}
		
	}
	public void goTo(Point desti) {
		double dir = Math.atan(desti.y/desti.x) - Math.atan(y/x);
		System.out.println(Math.cos(dir));
		x+=0.5*Math.cos(dir);
		y+=0.5*Math.sin(dir);
	}

	public void render(Graphics g) {
		g.drawRect(x, y, 1, 1);
		g.setColor(Color.red);
		g.drawRect(destination.x, destination.y, 1, 1);
		g.setColor(Color.white);
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
