package pop;


public class Production {
	
	private Map <Good,Integer> output;
	private Map <Good,Integer> input;
	/*	
	private List <Good> output;
	private List <Good> input;
	private int outputAmount = 0,inputAmount = 0;
	*/
	private float productionTime = 0;
	
	public Production(Good output, int amount) {
		this.output = output;
		this.outputAmount = amount;
		output = new LinkedList<Good>();
		input = new LinkedList<Good>();
		
	}
	
	public HashMap<Good,Integer> getOutput() {
		return output;
	}
	
	public HashMap<Good,Integer> getInput() {
		return input;
	}
	
	public float getProductionTime() {
		return productionTime;
	}
	
	public Production setOutput(HashMap<Good,Integer> output) {
		this.output = output;
		return this;
	}
	public Production setInput(HashMap<Good,Integer> input) {
		this.input = input;
		return this;
	}
	public Production setProductionTime(float productionTime) {
		this.productionTime = productionTime;
		return this;
	}
	

}
