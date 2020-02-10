package com.entity.object;

import com.Main.Screen;
import com.entity.Entity;
import com.entity.mob.Mob;
import com.entity.mob.Player;
import com.graphics.Sprite;

public class PlayerAnimObject extends Object{
	
	protected Sprite sprite2, currentSprite;

	public PlayerAnimObject(int x, int y, Sprite sprite, Sprite sprite2) {
		super(x, y, sprite);
		currentSprite = sprite;
		this.sprite2 = sprite2;
	}
	
	public void update() {
		Player player = level.getPlayer();
		double dx = Math.abs(x/16 - player.getTileX());
		double dy = Math.abs(y/16 - player.getTileY());
		double distance = Math.sqrt(Math.abs(dx*dx) + Math.abs(dy*dy));
		
		if(distance >= 1.1) {
			for(Entity e: level.getEntities()) {
				if(e instanceof Mob) {
					dx = Math.abs(x/16 - e.getTileX());
					dy = Math.abs(y/16 - e.getTileY());
					double d = Math.sqrt(Math.abs(dx*dx) + Math.abs(dy*dy));
					if(d < 1.5) distance = d;
				}
			}
		}
		
		if(distance < 1.1) currentSprite = sprite2;
		else currentSprite = sprite;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int)x, (int)y, currentSprite, true);
	}

}
