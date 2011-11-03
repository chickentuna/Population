package pop;


public class Production {
	
	private Good output = Good.NONE;
	private Good input = Good.NONE;
	private int outputAmount = 0,inputAmount = 0;
	private float productionTime = 0;
	
	public Production(Good output, int amount) {
		this.output = output;
		this.outputAmount = amount;
	}
	public Good getOutput() {
		return output;
	}
	public Good getInput() {
		return input;
	}
	public int getOutputAmount() {
		return outputAmount;
	}
	public int getInputAmount() {
		return inputAmount;
	}
	public float getProductionTime() {
		return productionTime;
	}
	
	public Production setOutput(Good output) {
		this.output = output;
		return this;
	}
	public Production setInput(Good input) {
		this.input = input;
		return this;
	}
	public Production setOutputAmount(int outputAmount) {
		this.outputAmount = outputAmount;
		return this;
	}
	public Production setInputAmount(int inputAmount) {
		this.inputAmount = inputAmount;
		return this;
	}
	public Production setProductionTime(float productionTime) {
		this.productionTime = productionTime;
		return this;
	}
	

}
