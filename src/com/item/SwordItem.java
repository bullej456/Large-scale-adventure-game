package com.item;

import com.graphics.AnimatedSprite;
import com.graphics.SpriteSheet;

public class SwordItem extends Item{
	
	public SwordItem(int tx, int ty) {
		super();
		up = new AnimatedSprite(SpriteSheet.swordU, 24, 24, 4);
		right = new AnimatedSprite(SpriteSheet.swordR, 24, 24, 4);
		down = new AnimatedSprite(SpriteSheet.swordD, 24, 24, 4);
		left = new AnimatedSprite(SpriteSheet.swordL, 24, 24, 4);
		sizeX = 24;
		sizeY = 24;
		animSprite = up;
		up.setFrameRate(4);
		down.setFrameRate(4);
		left.setFrameRate(4);
		right.setFrameRate(4);
		
		x = tx<<4;
		y = ty<<4;
	}

}
