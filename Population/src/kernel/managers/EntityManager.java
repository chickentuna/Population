package kernel.managers;

import java.util.ArrayList;
import java.util.Iterator;

import org.newdawn.slick.Graphics;

import kernel.Entity;


public class EntityManager {

	public ArrayList<Entity> entities;

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
		entities = new ArrayList<Entity>();
	}

	public Entity spawn(Entity o) {
		entities.add(o);
		return o;
	}

	public boolean unspawn(Entity o) {
		o.destroy();
		return entities.remove(o);
	}

	public Entity get(int k) {
		return entities.get(k);
	}

	public ArrayList<Entity> get(Class<?> clazz) {
		ArrayList<Entity> array = new ArrayList<Entity>();
		for (int k=0;k<entities.size();k++) {
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
	}

	public void render(Graphics g) {
		Iterator<Entity> it = entities.iterator();
		while (it.hasNext()) {
			it.next().render(g);
		}
		
	}
	
}
