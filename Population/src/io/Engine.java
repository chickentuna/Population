package io;

import kernel.managers.EntityManager;
import kernel.managers.Managers;
import kernel.managers.WorldManager;
import model.Behaviour;
import model.Villager;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.AppGameContainer;

public class Engine extends BasicGame {
	public static final int TARGET_FPS = 30;
	
	public String cheat;
	
	public Engine() {
		super("Population");
		cheat="";
	}	
	
	@Override
	public void init(GameContainer container) throws SlickException {
		
		Managers.cleanInit();
		Villager v = new Villager(125,125);
		EntityManager.get().spawn(v);
		v.adoptBehaviour(Behaviour.CURIOSITY);

	}
	
	@Override
	public void keyPressed(int i, char c) {
		if (Character.isLetter(c))
			cheat=cheat+c;
		if (i==28) {
			Cheat.perform(cheat);
			cheat="";
		}	
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException {
		EntityManager.get().update();
	}

	
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
		WorldManager.get().render(g);
		EntityManager.get().render(g);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Engine());
			app.setTargetFrameRate(TARGET_FPS);
			app.setDisplayMode(640, 480, false);
			app.start();
			} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}