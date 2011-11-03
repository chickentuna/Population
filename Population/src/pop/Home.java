package pop;

import java.util.LinkedList;
import java.util.List;

public class Home extends Building {
	private int capacity;
	List<Villager> occupants;
	
	public Home() {
		occupants = new LinkedList<Villager>();
		type = Building.Type.HOME;
	}
	
	public void addOccupant(Villager occupant) {
		occupants.add(occupant);
	}
	
	public Home setCapacity(int capacity) {
		this.capacity = capacity;
		return this;
	}
	public int getCapacity() {
		return capacity;
	}
	
}
