package test;

import java.util.LinkedList;

import kernel.Chance;
import kernel.Decision;

public class ChanceTest {

	public static void main(String[] args) {
		int ones = 0;
		int twos = 0;
		int threes = 0;
		int fours = 0;

		LinkedList<Decision> ds = new LinkedList<>();
		Decision d1 = new Decision() {
			@Override
			public int getWeight() {
				return 1;
			}

			@Override
			public Object getParam() {
				return null;
			}
		};
		Decision d2 = new Decision() {
			@Override
			public int getWeight() {
				return 2;
			}

			@Override
			public Object getParam() {
				return null;
			}
		};
		Decision d3 = new Decision() {
			@Override
			public int getWeight() {
				return 3;
			}

			@Override
			public Object getParam() {
				return null;
			}
		};
		Decision d4 = new Decision() {
			@Override
			public int getWeight() {
				return 1;
			}

			public Object getParam() {
				// TODO Auto-generated method stub
				return null;
			}
		};

		ds.add(d1);
		ds.add(d2);
		ds.add(d3);
		ds.add(d4);

		for (int i = 0; i < 1000; i++) {

			Decision d = Chance.pickFrom(ds);
			if (d == d1) {
				ones++;
			} else if (d == d2) {
				twos++;
			} else if (d == d3) {
				threes++;
			} else if (d == d4) {
				fours++;
			}
		}

		System.out.println("1 : " + ones + "\n2 : " + twos + "\n3 : " + threes
				+ "\n4 : " + fours);

	}

}
