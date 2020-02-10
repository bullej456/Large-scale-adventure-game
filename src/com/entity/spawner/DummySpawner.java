package com.entity.spawner;

import com.entity.mob.Dummy;
import com.level.Level;

public class DummySpawner extends Spawner{

	public DummySpawner(int x, int y, int amount, Level level) {
		super(x, y, Type.MOB, amount, level);
		for(int i = 0; i < amount; i++) {
			Dummy chaser = new Dummy(x, y, 30);
			level.add(chaser);
			entities.add(chaser);
		}
	}
	
	public void update() {
		for(int i = 0; i < amount; i++) {
			if(entities.get(i).isRemoved()) {
				entities.remove(entities.get(i));
				Dummy chaser = new Dummy((int)x, (int)y, 30);
				level.add(chaser);
				entities.add(chaser);
			}
		}
	}
}
