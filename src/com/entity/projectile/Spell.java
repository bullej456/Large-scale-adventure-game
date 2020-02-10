package com.entity.projectile;

import com.entity.Entity;
import com.graphics.Sprite;

public class Spell extends Projectile{

	public Spell(double x, double y, double dir, Entity e) {
		super(x, y, dir, e);
		sprite = Sprite.particleBlue;
		range = 180 + random.nextInt(50);
		speed = 2;
		damage = 40 + random.nextInt(12);
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		blast = true;
		FIRE_RATE = 35;
	}
	
	protected void move() {
		x += nx + ny*Math.sin((2*Math.PI*y)/(ny*14));
		y += ny + nx*Math.sin((2*Math.PI*x)/(nx*14));
		if(distance() > range) remove();
	}
	
}
