package pop;

import java.util.LinkedList;
import java.util.List;

public class HousingBuilding extends Building {
	List<Villager> occupants;
	private int capacity;

	public HousingBuilding(int capacity) {
		this.capacity = capacity;
		occupants = new LinkedList<Villager>();
	}

	public boolean addOccupant(Villager occupant) {
		if (isFull()) {
			occupants.add(occupant);
			return true;
		}
		return false;
	}
	
	public boolean isFull() {
		return occupants.size() < capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
}
