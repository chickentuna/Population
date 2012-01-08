package pop;

public class Job {
		
	private Building workplace;
	private Type type;

	public enum Type { NONE, FARMER, MINER, COURTESAN, LUMBERJACK }
	
	@Override
	public String toString() {
		return "Job [workplace=" + workplace + ", type=" + type + "]";
	}

	public Job(pop.Job.Type type) {
		this.type = type;
	}

	public Job setType(Type type) {
		this.type = type;
		return this;
	}

	public Type getType() {
		return type;
	}

	public Job setWorkplace(Building workplace) {
		this.workplace = workplace;
		return this;
	}

	public Building getWorkplace() {
		return workplace;
	}
	
	public static Behaviour getBehaviour(Type type) {
		if (type==Type.LUMBERJACK) {
			return new Behaviour() {
			@Override
			public void update(Villager v) {
				//find a tree
			}};
		}
		return null;
	}
	
}
