package strategy;

public class Executor {
	
	
	Strategy mystrategy;
	
	public void setStrategy(Strategy strategy) {
		this.mystrategy = strategy;
	}
	
	public void dostrat() {
		mystrategy.execute();
	}

}
