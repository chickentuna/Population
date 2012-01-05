package strategy;

public class Main {
	
	
	public static void main(String[] args) {
		Executor e = new Executor();
		
		e.setStrategy(new Strategy() {
			@Override
			public void execute() {
				System.out.printf("coucou 1\n");
			}
		});
		
		e.dostrat();
		
		e.setStrategy(new Strategy() {
			@Override
			public void execute() {
				System.out.printf("coucou 2\n");
			}
		});

	
		e.dostrat();

	}

}
