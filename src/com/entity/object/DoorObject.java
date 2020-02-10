package com.entity.object;

import com.entity.Entity;
import com.entity.mob.Mob;
import com.entity.mob.Player;
import com.graphics.Sprite;

public class DoorObject extends PlayerAnimObject{

	public DoorObject(int x, int y, Sprite sprite, Sprite sprite2) {
		super(x, y, sprite, sprite2);
		solid = true;
	}
	
	public void update() {
		Player player = level.getPlayer();
		double dx = Math.abs(x/16 - player.getTileX());
		double dy = Math.abs(y/16 - player.getTileY());
		double distance = Math.sqrt(Math.abs(dx*dx) + Math.abs(dy*dy));
		if(distance >= 1.5) {
			for(Entity e: level.getEntities()) {
				if(e instanceof Mob) {
					dx = Math.abs(x/16 - e.getTileX());
					dy = Math.abs(y/16 - e.getTileY());
					double d = Math.sqrt(Math.abs(dx*dx) + Math.abs(dy*dy));
					if(d < 1.5) distance = d;
				}
			}
		}
		
		if(distance < 1.5) {
			currentSprite = sprite2;
			solid = false;
		}
		else{
			currentSprite = sprite;
			solid = true;
		}
	}

}
