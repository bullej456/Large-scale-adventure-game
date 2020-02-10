package com.item;

import com.entity.mob.Mob;
import com.entity.mob.Player;
import com.graphics.AnimatedSprite;
import com.graphics.FloatFont;
import com.graphics.SpriteSheet;

public class XPOrbItem extends Item{
	
	public XPOrbItem(int tx, int ty) {
		super();
		up = new AnimatedSprite(SpriteSheet.xpOrb, 16, 16, 4);
		right = new AnimatedSprite(SpriteSheet.xpOrb, 16, 16, 4);
		down = new AnimatedSprite(SpriteSheet.xpOrb, 16, 16, 4);
		left = new AnimatedSprite(SpriteSheet.xpOrb, 16, 16, 4);
		animSprite = up;
		
		x = (tx<<4) + (random.nextInt(16) - 8);
		y = (ty<<4) + (random.nextInt(16) - 8);
	}
	
	public void update() {
		if(held) {
			
		}
		else {
			if(floatCounter > 30 && floatUp) floatUp = false;
			if(floatCounter < 0 && !floatUp) floatUp = true;
			if(floatUp) {
				y -= 0.15f;
				floatCounter++;
			}
			else{
				y += 0.15f;
				floatCounter--;
			}
			time++;
			if(time >= 60) pickable = true;
			
			chase();
			despawnUpdate();
		}
	}
	
	private void chase() {
		boolean chase = false;
		Player player = level.getPlayer();
		int dx = Math.abs((int)player.getX() - (int)x);
		int dy = Math.abs((int)player.getY() - (int)y);
		double distance = Math.sqrt((dx*dx)*(dy*dy));
		if(distance <= 120) chase = true;
		
		if(chase) {
			if(x < player.getX() && dx > 2) x += 2;
			else if(x > player.getX() && dx > 2) x -= 2;
			if(y < player.getY() && dy > 2) y += 2;
			else if(y > player.getY() && dy > 2) y -= 2;
		}
	}
	
	public void pickup(Mob mob) {
		this.mob = mob;
		if(level != null) level.add(new FloatFont((int)x>>4, (int)y>>4, "+5", 50, 0xcacaff));
		if(this.mob.equals(level.getPlayer())) Player.xpm.addXP(5);
	}
	
	protected void despawnUpdate() {
		despawnTimer++;
		if(despawnTimer >= despawnTime) {
			despawn = true;
			remove();
		}
	}

}
