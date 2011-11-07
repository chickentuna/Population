package test;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.Sound;
import org.newdawn.slick.util.ResourceLoader;

public class Engine extends BasicGame {
	public Engine() {
		super("Wally the Wombat");
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