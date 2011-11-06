package pop;

public class Production {
	
	private GoodMap output;
	private GoodMap input;

	private float productionTime = 0;
	
	public Production(GoodMap output) {
		this.output = output;
		input = new GoodMap();
		
	}
	
	public GoodMap getOutput() {
		return output;
	}
	
	public GoodMap getInput() {
		return input;
	}
	
	public float getProductionTime() {
		return productionTime;
	}
	
	public Production setOutput(GoodMap output) {
		this.output = output;
		return this;
	}
	public Production setInput(GoodMap input) {
		this.input = input;
		return this;
	}
	public Production setProductionTime(float productionTime) {
		this.productionTime = productionTime;
		return this;
	}
	

}
