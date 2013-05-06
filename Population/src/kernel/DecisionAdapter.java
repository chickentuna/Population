package kernel;

public class DecisionAdapter implements Decision {

	int weight;
	Object param;
	
	public DecisionAdapter(int weight, Object param) {
		this.weight = weight;
		this.param = param;
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public Object getParam() {
		return param;
	}

	
}
