package test;

import java.util.List;

import pop.*;
import pop.ProductionBuilding.Position;
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
		System.out.println(farm.canStartProduction());
	}

}
