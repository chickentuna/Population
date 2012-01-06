package test;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import pop.Villager.Sex;
import pop.*;

public class WindowlessEngine {
	public WindowlessEngine() {
		
	}


	public void init() {
		EntityManager.init();
		
		for (int i=0;i<5;i++) {
			Villager v = new Villager(300f,200f);
			if ((int)(Math.random()*2) == 0)
				v.setSex(Sex.FEMALE);			
		}
		
		
		
	}


	public void update() {
		for (int k=0;k<EntityManager.size();k++) {
			EntityManager.get(k).update();
		}
	}


	public void render(Graphics g)  {
		for (Entity e : EntityManager.entities) {
			e.render(g);
		}		
	}

	public static void main(String[] args) {
		WindowlessEngine w = new WindowlessEngine();
		boolean run=true;
		w.init();
		while (run) {
			w.update();
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}