package kernel.managers;

import io.GameBus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import kernel.Entity;
import kernel.events.EntityRenameEvent;

import com.google.common.collect.Lists;

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

		List<Runnable> todos = Lists.newArrayList();

		for (final Entity e : toUnspawn) {
			entities.remove(e);
			todos.add(new Runnable() {
				public void run() {
					e.destroy();
				}
			});
		}
		for (Runnable r : todos) {
			r.run();
		}

		toUnspawn.clear();

		for (Entity e : toSpawn) {
			entities.add(e);
		}
		toSpawn.clear();
	}

}
