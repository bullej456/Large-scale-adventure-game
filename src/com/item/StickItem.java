package com.item;

import com.graphics.AnimatedSprite;
import com.graphics.SpriteSheet;

public class StickItem extends Item{

	public StickItem(int tx, int ty) {
		super();
		up = new AnimatedSprite(SpriteSheet.stickU, 16, 16, 4);
		right = new AnimatedSprite(SpriteSheet.stickR, 16, 16, 4);
		down = new AnimatedSprite(SpriteSheet.stickD, 16, 16, 4);
		left = new AnimatedSprite(SpriteSheet.stickL, 16, 16, 4);
		animSprite = up;
		up.setFrameRate(6);
		down.setFrameRate(6);
		left.setFrameRate(6);
		right.setFrameRate(6);
		
		x = tx<<4;
		y = ty<<4;
	}

}
