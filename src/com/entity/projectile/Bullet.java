package com.entity.projectile;

import com.entity.Entity;
import com.graphics.Sprite;

public class Bullet extends Projectile{
	
	public Bullet(double x, double y, double dir, Entity e) {
		super(x, y, dir + ((random.nextInt(100)-50)/300f), e);
		sprite = Sprite.particlePink;
		range = 180 + random.nextInt(50);
		speed = 6;
		damage = 16 + random.nextInt(6);
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		FIRE_RATE = 7;
	}
	
}
