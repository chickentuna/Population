

import java.util.LinkedList;

import kernel.Chance;
import kernel.Decision;

public class ChanceTest {

	public static void main(String[] args) {

	}

	public static void onceEveryXSecondsTest() {
		int n1 = 0;
		int n2 = 0;
		int n10 = 0;
		int n20 = 0;

		long start = System.currentTimeMillis();

		while (System.currentTimeMillis() - start < 120_000) {

			if (Chance.onceEveryXSeconds(1))
				n1++;
			if (Chance.onceEveryXSeconds(2))
				n2++;
			if (Chance.onceEveryXSeconds(10))
				n10++;
			if (Chance.onceEveryXSeconds(20))
				n20++;
		}
		System.out.println("1 : " + n1 + "\n2 : " + n2 + "\n10 : " + n10
				+ "\n20 : " + n20);
	}

	public static void oneOutOfTest() {
		int n = 0;

		for (int i = 0; i < 10000; i++) {
			if (Chance.oneOutOf(500))
				n++;
		}
		System.out.println(n);

	}

	public static void pickFromTest() {
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
