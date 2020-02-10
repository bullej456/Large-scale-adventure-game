package com.item;

import com.Main.Main;
import com.entity.mob.Mob;
import com.graphics.AnimatedSprite;
import com.graphics.FloatFont;
import com.graphics.SpriteSheet;

public class HeartItem extends Item{
	
	public HeartItem(int tx, int ty) {
		super();
		up = new AnimatedSprite(SpriteSheet.heart, 16, 16, 4);
		right = new AnimatedSprite(SpriteSheet.heart, 16, 16, 4);
		down = new AnimatedSprite(SpriteSheet.heart, 16, 16, 4);
		left = new AnimatedSprite(SpriteSheet.heart, 16, 16, 4);
		animSprite = up;
		
		x = tx<<4;
		y = ty<<4;
	}
	
	public void pickup(Mob mob) {
		this.mob = mob;
		if(level != null) level.add(new FloatFont((int)x>>4, (int)y>>4, "+10", 50, 0x00ff00));
		if(this.mob.equals(level.getPlayer())) {
			Main.data.health += 10;
			if(Main.data.health > Main.data.maxHealth) Main.data.health = Main.data.maxHealth;
		}
	}
	
	protected void despawnUpdate() {
		despawnTimer++;
		if(despawnTimer >= despawnTime) {
			despawn = true;
			remove();
		}
	}
}
