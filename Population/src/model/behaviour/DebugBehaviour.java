package model.behaviour;

import kernel.Point;
import model.VState;
import model.Villager;

import org.newdawn.slick.Input;

public final class DebugBehaviour extends Behaviour {

	private Input userInput;

	public DebugBehaviour(Input userInput) {
		this.userInput= userInput ;
		
	}
	
	@Override
	public void onAdopt(Villager owner) {
		owner.setState(VState.IDLE);
	}

	@Override
	protected void execution(Villager owner) {
		//Go to mouse
		float mouseX = userInput.getMouseX();
		float mouseY = userInput.getMouseY();
		
		Point to = new Point(mouseX, mouseY);
		owner.step_towards(to);
		
	}

}
