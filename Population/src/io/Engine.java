package io;

import kernel.managers.EntityManager;
import kernel.managers.Managers;
import kernel.managers.RessourceManager;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import view.DebugView;
import view.View;

import com.google.common.eventbus.EventBus;

public class Engine extends BasicGame {
	public static final int TARGET_FPS = 30;

	public String cheat;
	public View view;

	public Engine() {
		super("Population");
		cheat = "";
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// Start Managers
		Managers.cleanInit();

		// Start View
		view = new DebugView();
		GameBus.register(view);

		// Start Game
		RessourceManager.get().villagerBirth(125, 125);
		RessourceManager.get().villagerBirth(100, 100);
		RessourceManager.get().villagerBirth(250, 125);
		RessourceManager.get().villagerBirth(125, 250);
		RessourceManager.get().villagerBirth(0, 0);

	}

	@Override
	public void keyPressed(int i, char c) {
		if (Character.isLetter(c))
			cheat = cheat + c;
		if (i == 28) {
			Cheat.perform(cheat);
			cheat = "";
		}
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		EntityManager.get().update();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		view.render(g);
		EntityManager.get().render(g);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Engine());
			app.setTargetFrameRate(TARGET_FPS);
			app.setDisplayMode(640, 480, false);
			app.setResizable(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}