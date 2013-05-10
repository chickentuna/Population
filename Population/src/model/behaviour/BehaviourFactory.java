package model.behaviour;

public class BehaviourFactory {
	
	public BehaviourFactory() {
		
	}
	
	public Behaviour create(BehaviourTypes bt) {
		return bt.getBase();
	}
}
