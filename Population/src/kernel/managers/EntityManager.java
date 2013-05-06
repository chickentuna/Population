package kernel.managers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import kernel.Entity;

import org.newdawn.slick.Graphics;

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
		return o;
	}

	// TODO: spawn & unspawn : no concurrent modification of list. Must use
	// refresh system
	public void unspawn(Entity o) {
		toUnspawn.add(o);
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
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			it.next().update();
		}
		refreshEntities();
	}

	private void refreshEntities() {
		Iterator<Entity> it = toUnspawn.iterator();
		while (it.hasNext()) {
			Entity b = it.next();
			entities.remove(b);
			b.destroy();
		}
		toUnspawn.clear();
		it = toSpawn.iterator();
		while (it.hasNext()) {
			Entity b = it.next();
			entities.add(b);
		}
		toSpawn.clear();

	}

}
