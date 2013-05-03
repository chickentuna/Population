package test;

import model.nature.Land;

import org.junit.Test;

import technology.BType;
import technology.Building;
import technology.RBMaterial;
import technology.ResidentialBuilding;

public class ResidentialBuildingTest {

	@Test
	public void test() {

	}

	class RB implements Building {
		ResidentialBuilding type;
		RBMaterial material;

		RB(ResidentialBuilding type, RBMaterial material) {
			this.type = type;
			this.material = material;
		}

		@Override
		public Land getLand() {
			return material.getLand();
		}

		@Override
		public BType getType() {
			return BType.RESIDENTIAL;
		}

	}

}
