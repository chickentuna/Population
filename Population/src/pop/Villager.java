package pop;

import java.awt.Point;
import java.lang.Math;
import java.util.ArrayList;

import mapping.Ressource;
import mapping.Tiles;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import strategy.Strategy;

public class Villager extends Entity {

	static final int MAX = 10;

	private String name, surname;
	private Job job;
	private Point destination = new Point();
	private ResidentialBuilding home;
	private Sex sex = Sex.MALE;
	private State state = State.IDLE;
	private GoodMap carrying;
	private float speed = 0.5f;
	private double direction = 0;

	public enum Sex {
		MALE, FEMALE
	}

	public enum State {
		IDLE, WORKING, LOITERING, CARRYING, CARRYINGTO, GOINGTO, BUILDING
	}

	public Villager(float x, float y) {
		super(x, y);
		carrying = new GoodMap();
		state = State.IDLE;
	}

	/**
	 * Update method for standard villagers.
	 * 
	 * @author Julien
	 */
	public void update() {
		/*
		 * TODO: implement a probabilistic algorithm to adopt this behaviour:
		 * 1-Wander around 2-Find a home, or build one 3-If a resource is
		 * spotted, search for nearby production building or build one, then
		 * work in it 4-Quit working somewhere
		 * 
		 * That's all for now.
		 */

		double dice = (Math.random() * 3000);
		if (dice <= 10) {
			if (home == null) {
				home = EntityManager.getFreeHome();
				if (home != null) {
					home.addOccupant(this);
				} else {
					build(new Hovel());
					state = State.IDLE;
				}
			}
		}
		if (state == State.IDLE) {
			if (dice < 50) {
				direction = Math.random() * 360;
				state = State.LOITERING;
			}
		}
		if (state == State.LOITERING) {
			x += speed * Math.cos(direction);
			y -= speed * Math.sin(direction);
			int myTile = EntityManager.map.tm.getTileId((int) x / 32,
					(int) y / 32, 0);
			int myItem = EntityManager.map.tm.getTileId((int) x / 32,
					(int) y / 32, 1);

			if (Tiles.impracticable(myTile, myItem)) {
				direction += 180;
				x += speed * Math.cos(direction);
				y -= speed * Math.sin(direction);
			}
			if (dice < 20)
				state = State.IDLE;
		}

		for (Entity res : EntityManager.get(Ressource.class)) {
			if (distanceTo(res) <= 2) {
				if (dice <= 2) {
					ProductionBuilding bui = ((Ressource) res).getReqBuilding();
					//Find production building
					ProductionBuilding goal = null;
					for (Entity pb : EntityManager.get(bui.getClass())) {
						if (distanceTo(pb) <= 5 && !((ProductionBuilding)pb).isFull()) {
							goal=(ProductionBuilding) pb;
							break;
						} 							
					}
					if (goal!=null) {
						goal.employ(this);
					} else {
						build(bui);
						bui.employ(this);
					}


				}
			}
		}

	}

	private void build(Building b) {
		if (EntityManager.freeArea((int) (x / 32), (int) (y / 32),
				b.getWidth(), b.getHeight())) {
			b.setLocation((int) (x / 32) * 32, (int) (y / 32) * 32);
			EntityManager.spawn(b);
		}
	}

	public void goTo(Point desti) {
		double dir = Math.atan((-(desti.getY() - y) / (desti.getX() - x)));
		if (desti.getX() < x)
			dir += Math.toRadians(180);
		x += speed * Math.cos(dir);
		y -= speed * Math.sin(dir);
		if (Math.sqrt((Math.pow(desti.getX() - x, 2))
				+ Math.pow(desti.getY() - y, 2)) <= speed) {
			state = State.IDLE;
		}
	}

	public void render(Graphics g) {
		if (sex == Sex.FEMALE)
			g.setColor(Color.red);
		else
			g.setColor(Color.black);
		
		g.drawRect(x, y, 1, 1);
		g.setColor(Color.white);
	}

	// TODO: Transfer part of this method to GoodMap as "take"
	public void collect(GoodMap pile) {
		state = State.CARRYING;
		for (Good good : pile.keySet()) {
			int k = pile.get(good);
			if (carrying.getAmount() < MAX) {
				if (k >= MAX - carrying.getAmount()) {
					pile.put(good, k - MAX - carrying.getAmount());
					carrying.put(good, MAX - carrying.getAmount());
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
