package test;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

public class Engine extends BasicGame {
	public Engine() {
		super("Population");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {

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