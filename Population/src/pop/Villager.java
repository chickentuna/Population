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
	private float speed=0.5f;
	
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
			if (home!=null) {
				home.addOccupant(this);
			} else {
				new Hovel(x,y);
			}
			
		}
		if (state==State.IDLE && job==null) {
			destination.x=(int) (x+Math.random()*200-100);
			destination.y=(int) (y+Math.random()*200-100);
			if (destination.x<0)
				destination.x+=200;
			else if (destination.x>=640)
				destination.x-=200;
			if (destination.y<0)
				destination.y+=200;
			else if (destination.y>=480)
				destination.y-=200;
			
			state=State.LOITERING;
		}
		if (state==State.LOITERING) {
			goTo(destination);
		}
		
	}
	public void goTo(Point desti) {
		double dir = Math.atan((-(desti.getY()-y)/(desti.getX()-x)));
		if (desti.getX()<x)
			dir+=Math.toRadians(180);
		x+=speed*Math.cos(dir);
		y-=speed*Math.sin(dir);
		if (Math.sqrt((Math.pow(desti.getX()-x, 2))+Math.pow(desti.getY()-y, 2)) <= speed ) {
			state=State.IDLE;
		}
	}

	public void render(Graphics g) {
		
		g.drawRect(x, y, 1, 1);
		
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
