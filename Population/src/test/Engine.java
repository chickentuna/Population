package test;

import java.util.ArrayList;

import farm.WheatFarm;

import pop.*;

public class Engine {

	private static boolean running;
	private static ArrayList<Entity> entities;

	/**
	 * Boucle de jeu principale.
	 */
	public static void run() {
		running = true;
		entities = new ArrayList<Entity>();
		
		initRoom();
		
		while (running) {
			for (Entity e : entities) {
				e.step();
			}
			try {
				Thread.sleep(20);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}


	}

	
	private static void initRoom() {
		Villager bob=(Villager) Engine.create(new Villager());
		Villager mike=(Villager) Engine.create(new Villager());
		Villager luke=(Villager) Engine.create(new Villager());
		Villager jen=(Villager) Engine.create(new Villager());
		WheatFarm farm = (WheatFarm) Engine.create(new WheatFarm());
		Position pos = farm.getWorkforce().get(0);
		pos.employ(bob,mike,luke,jen);
		farm.startProduction();
		
	}

	/**
	 * Arrête le moteur de jeu à la prochaine occurence de la boucle principale.
	 */
	public static void stop() {
		running = false;
	}


	public static Entity create(Entity e) {
		entities.add(e);
		return e;
		
	}

}