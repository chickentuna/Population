package kernel.managers;

import io.GameBus;

import java.util.ArrayList;
import java.util.LinkedList;

import kernel.Entity;
import kernel.events.EntityRenameEvent;

public class EntityManager {

	public ArrayList<Entity> entities;
	public LinkedList<Entity> toSpawn;
	public LinkedList<Entity> toUnspawn;

	private static EntityManager self = null;

	public static EntityManager get() {
		if (self == null) {
			self = new EntityManager();
		}
		return self;
	}

	public EntityManager(String mode) {
		this();
	}

	public EntityManager() {
		entities = new ArrayList<>();
		toSpawn = new LinkedList<>();
		toUnspawn = new LinkedList<>();
	}

	public Entity spawn(Entity o) {
		toSpawn.add(o);
		GameBus.post(new EntityRenameEvent(EntityRenameEvent.SPAWN, o));
		return o;
	}

	// TODO: spawn & unspawn : no concurrent modification of list. Must use
	// refresh system
	public void unspawn(Entity o) {
		toUnspawn.add(o);
		GameBus.post(new EntityRenameEvent(EntityRenameEvent.UNSPAWN, o));
	}

	public Entity get(int k) {
		return entities.get(k);
	}

	public ArrayList<Entity> get(Class<?> clazz) {
		ArrayList<Entity> array = new ArrayList<Entity>();
		for (int k = 0; k < entities.size(); k++) {
			if (clazz.isInstance(entities.get(k))) {
				array.add(entities.get(k));
			}
		}
		return array;
	}

	public void update() {
		refreshEntities();
		for (Entity e : entities) {
			e.update();
		}
	}

	private void refreshEntities() {
		
		for (Entity e : toUnspawn) {
			entities.remove(e);
			e.destroy();//TODO:This may modify entity list, so put it in differed execution
		}
		toUnspawn.clear();
		
		for (Entity e : toSpawn) {
			entities.add(e);
		}
		toSpawn.clear();
	}

}
