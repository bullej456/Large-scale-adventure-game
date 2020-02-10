package com.item;

import com.graphics.AnimatedSprite;
import com.graphics.SpriteSheet;

public class HandItem extends Item{

	public HandItem(int tx, int ty) {
		super();
		up = new AnimatedSprite(SpriteSheet.handU, 16, 16, 4);
		right = new AnimatedSprite(SpriteSheet.handR, 16, 16, 4);
		down = new AnimatedSprite(SpriteSheet.handD, 16, 16, 4);
		left = new AnimatedSprite(SpriteSheet.handL, 16, 16, 4);
		animSprite = up;
		up.setFrameRate(10);
		down.setFrameRate(10);
		left.setFrameRate(10);
		right.setFrameRate(10);
		
		x = tx<<4;
		y = ty<<4;
	}

}
