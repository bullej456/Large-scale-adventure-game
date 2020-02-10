package com.entity.mob;

import com.Main.Main;
import com.graphics.AnimatedSprite;
import com.graphics.SpriteSheet;
import com.item.HeartItem;

public class Goblin extends Chaser{
	
	public Goblin(int x, int y, int despawnRange) {
		super(x, y, despawnRange);
		up = new AnimatedSprite(SpriteSheet.banditU, 16, 16, 4);
		down = new AnimatedSprite(SpriteSheet.banditD, 16, 16, 4);
		left = new AnimatedSprite(SpriteSheet.banditL, 16, 16, 4);
		right = new AnimatedSprite(SpriteSheet.banditR, 16, 16, 4);
		down.setFrameRate(8);
		up.setFrameRate(8);
		left.setFrameRate(8);
		right.setFrameRate(8);
		animSprite = down;
		
		speed = 1.05;
		range = 90;
		health = 80;
		maxHealth = 80;
		attackDamage = 7;
	}
	
	protected void dead() {
		if(random.nextInt(4) == 0) level.add(new HeartItem((int)x>>4, (int)y>>4));
		int iMax = random.nextInt(3) + 2;
		//for(int i = 0; i < iMax; i++)
			//level.add(new XPOrbItem((int)x>>4, (int)y>>4));
		remove();
		Main.musicPlayer.playSound(8, -6f-1.5f*(float)level.calcDist(level.getPlayer().getTileX(), level.getPlayer().getTileY(), (int)x>>4, (int)y>>4));
	}

}
