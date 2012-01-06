package test;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import pop.Villager.Sex;
import pop.*;

public class Engine extends BasicGame {
	public Engine() {
		super("Population");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		EntityManager.init();
		
		for (int i=0;i<300;i++) {
			Villager v = new Villager(300f,200f);
			if ((int)(Math.random()*2) == 0)
				v.setSex(Sex.FEMALE);			
		}
		
		
		
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		for (int k=0;k<EntityManager.size();k++) {
			EntityManager.get(k).update();
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
			app.setTargetFrameRate(60);
			app.start();
			} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}