package kernel;

import io.Engine;

import java.util.Iterator;
import java.util.List;

public class Chance {

	public static boolean oneOutOf(int n) {
		return (int) (Math.random() * n) == 0;
	}

	public static boolean onceEveryXSeconds(double x) {
		return oneOutOf((int) (x * Engine.TARGET_FPS));
	}

	public static boolean onceEveryXMinutes(double x) {
		return onceEveryXSeconds(x * 60);
	}

	public static boolean onceEveryXHours(double x) {
		return onceEveryXMinutes(x * 60);
	}

	public static Decision pickFrom(List<Decision> possibilities) {
		int count = possibilities.size();
		if (count == 1) {
			return possibilities.get(0);
		} else if (count == 0) {
			throw new RuntimeException(
					"Error : Picking a Decision from empty list");
		}
		int total = 0;

		Iterator<Decision> it = possibilities.iterator();
		while (it.hasNext()) {
			total += it.next().getWeight();
		}

		double percent = Math.random();
		int n = (int) (percent * total);
		int x = 0;
		it = possibilities.iterator();
		while (it.hasNext()) {
			Decision decision = it.next();
			x += decision.getWeight();
			if (x > n) {
				return decision;
			}
		}
		throw new RuntimeException("Error : no decision found in range n = "
				+ n + " for x = " + x);
	}

}
