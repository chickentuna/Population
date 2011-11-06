package test;

import pop.*;
import farm.*;

public class Main {

	public static void main(String[] args) {
		
		Villager bob = new Villager();
		Villager mike = new Villager();
		Villager luke = new Villager();
		Villager jen = new Villager();
		
		WheatFarm farm = new WheatFarm();
		Position pos = farm.getWorkforce().get(0);
		System.out.println(pos.employ(bob,mike,luke,jen));
		System.out.println(farm.isFull());
		System.out.println(farm.can());
		print(farm.startProduction());
	}
	
	public static void print(Object o) {
		System.out.println(o);
	}

}
