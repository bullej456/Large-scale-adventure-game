package com.entity.spawner;

import com.entity.BlastParticle;
import com.entity.Particle;
import com.level.Level;

public class ParticleSpawner extends Spawner{
	

	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		for(int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life, false));
		}
	}
	
	public ParticleSpawner(int x, int y, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		for(int i = 0; i < amount; i++) {
			level.add(new BlastParticle(x, y, 14, false));
		}
	}
	
	public ParticleSpawner(int x, int y, int life, int amount, Level level, boolean blood) {
		super(x, y, Type.PARTICLE, amount, level);
		for(int i = 0; i < amount; i++) {
			level.add(new Particle(x, y, life, blood));
		}
	}

}
