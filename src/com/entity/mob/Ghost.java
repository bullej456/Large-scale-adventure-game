package com.entity.mob;

import com.Main.Main;
import com.graphics.AnimatedSprite;
import com.graphics.SpriteSheet;
import com.item.HeartItem;
import com.item.XPOrbItem;

public class Ghost extends Chaser{

	public Ghost(int x, int y, int despawnRange) {
		super(x, y, despawnRange);
		up = new AnimatedSprite(SpriteSheet.ghostU, 16, 16, 4);
		down = new AnimatedSprite(SpriteSheet.ghostD, 16, 16, 4);
		left = new AnimatedSprite(SpriteSheet.ghostL, 16, 16, 4);
		right = new AnimatedSprite(SpriteSheet.ghostR, 16, 16, 4);
		animSprite = down;
	}
	
	protected void dead() {
		if(random.nextInt(4) == 0) level.add(new HeartItem((int)x>>4, (int)y>>4));
		int iMax = random.nextInt(3) + 2;
		for(int i = 0; i < iMax; i++)
			level.add(new XPOrbItem((int)x>>4, (int)y>>4));
		remove();
		Main.musicPlayer.playSound(8, -6f-1.5f*(float)level.calcDist(level.getPlayer().getTileX(), level.getPlayer().getTileY(), (int)x>>4, (int)y>>4));
	}

}
