package model;

import model.nature.Produce;

public abstract class Producer {
	
	protected Produce[] produce;
	
	public Produce getOneProduce() {
		if (produce == null || produce.length == 0) {
			return null;
		}
		if (produce.length == 1) {
			return produce[0];
		}
		return produce[(int)(Math.random()*produce.length)]; 
		//TODO: Produce should have a field 'chance' to indicate probability of finding it
		// for lands/buildings that produce multiple things
		
	}

	public int getWeight() {
		int weight = 0;
		if (produce!=null) {
			for (int k = 0; k<produce.length; k++) {
				weight += produce[k].getValue(); //TODO: ponderate with probability, when probablity is implemntd.
			}	
		}
		return weight;
	}
	
	public Produce[] getProduce() {
		return produce;
	}
}
