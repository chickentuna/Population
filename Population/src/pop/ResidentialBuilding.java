package pop;

import java.util.LinkedList;
import java.util.List;

public class ResidentialBuilding extends Building {
	List<Villager> occupants;
	private int capacity;

	public ResidentialBuilding(int capacity, float x, float y) {
		super(x,y);
		this.capacity = capacity;
		occupants = new LinkedList<Villager>();
		//type = Type.HOME;
	}

	public boolean addOccupant(Villager occupant) {
		if (spaceLeft()) {
			occupants.add(occupant);
			occupant.setHome(this);
			return true;
		}
		return false;
	}
	public boolean removeOccupant(Villager occupant) {
		return occupants.remove(occupant);
	}
	
	
	public boolean spaceLeft() {
		return occupants.size() < capacity;
	}
	
	public int getCapacity() {
		return capacity;
	}
}
