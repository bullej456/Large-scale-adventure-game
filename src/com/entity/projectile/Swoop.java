package com.entity.projectile;

import com.Main.Main;
import com.Main.Screen;
import com.entity.Entity;
import com.graphics.Sprite;

public class Swoop extends Projectile{
	
	public Swoop(double x, double y, double dir, Entity e) {
		super(x, y, dir, e);
		sprite = Sprite.nothing;
		range = 25;
		speed = 10;
		damage = 45 + random.nextInt(12);
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
		FIRE_RATE = 20;
	}
	
	public void update() {
		//change last parameter input.
		if(level.projCollision(x, y, nx, ny, 15, (int) damage, blast, e) == 2) {
			Main.musicPlayer.playSound(9, -10f-1.5f*(float)level.calcDist(level.getPlayer().getTileX(), level.getPlayer().getTileY(), (int)x>>4, (int)y>>4));
			remove();
		}
		else if(level.projCollision(x, y, nx, ny, 15, (int) damage, blast, e) == 1) {

			Main.musicPlayer.playSound(10, -10f-1.5f*(float)level.calcDist(level.getPlayer().getTileX(), level.getPlayer().getTileY(), (int)x>>4, (int)y>>4));
			remove();
		}
		move();
		
//		if(angle > 6) sprite = Sprite.swoopR;
//		else if(angle < 0) sprite = Sprite.swoopU;
//		else if(angle < 4 && angle > 3) sprite = Sprite.swoopL;
//		else sprite = Sprite.swoopD;
	}
	
	public void render(Screen screen) {
		//??????????
		screen.renderSprite((int) x - 8, (int) y - 8, sprite, true);
	}
	
}
