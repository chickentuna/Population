package kernel.events;

import kernel.Entity;

public class EntityRenameEvent {

	public static final int SPAWN = 0;
	public static final int UNSPAWN = 1;

	int type;
	Entity entity;

	public EntityRenameEvent(int type, Entity entity) {
		this.type = type;
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}

	public int getType() {
		return type;
	}
}
