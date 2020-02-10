package com.entity.object.active;

import com.entity.mob.Mob.Dir;
import com.entity.object.ActiveObject;
import com.entity.projectile.Bullet;
import com.entity.projectile.Spell;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;

public class ProjectileBoxObject extends ActiveObject{
	
	private Dir dir = Dir.DOWN;
	protected int fireRate = 20;

	public ProjectileBoxObject(int x, int y, Sprite sprite, SpriteSheet sheet, int rate, Dir dir) {
		super(x, y, sprite, sheet, rate);
		this.dir = dir;
		activate();
		projSolid = true;
	}
	
	protected void doThing() {
		if(fireRate > 0) fireRate--;
		if(fireRate <= 0) {
			double direction = -1;
			if(dir == Dir.UP) direction = -Math.PI/2;
			else if(dir == Dir.DOWN) direction = Math.PI/2;
			else if(dir == Dir.LEFT) direction = Math.PI;
			else if(dir == Dir.RIGHT) direction = 2*Math.PI;
			level.add(new Bullet(x+8, y+8, direction, this));
			fireRate = 20;
		}
	}

}
