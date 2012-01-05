package test;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

import pop.*;

public class Engine extends BasicGame {
	public Engine() {
		super("Population");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		EntityManager.init();
		Hovel house = new Hovel(10f,40f);
		Villager vil = new Villager(100f,100f);
		
		
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (Entity e : EntityManager.entities) {
			e.update();
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		for (Entity e : EntityManager.entities) {
			e.render(g);
		}
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Engine());
			
			app.start();
			} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}