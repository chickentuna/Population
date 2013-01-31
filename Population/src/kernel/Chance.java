package kernel;

public class Chance {
	
	public static boolean oneOutOf(int n) {
		return Math.random()*100 < 100/n;
	}
}
