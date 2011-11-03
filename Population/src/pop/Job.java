package pop;

public class Job {
		
	private Building workplace;
	private int type;

	public enum Type { NONE, FARMER, MINER, PROSTITUTE, PIMP }
	
	public Job setType(int type) {
		this.type = type;
		return this;
	}

	public int getType() {
		return type;
	}

	public Job setWorkplace(Building workplace) {
		this.workplace = workplace;
		return this;
	}

	public Building getWorkplace() {
		return workplace;
	}
}
