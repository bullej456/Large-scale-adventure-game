package com.entity.object;

import com.Main.Screen;
import com.graphics.AnimatedSprite;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;

public class AnimObject extends Object{
	
	protected AnimatedSprite animSprite;

	public AnimObject(int x, int y, Sprite sprite, SpriteSheet sheet, int rate, boolean solid, boolean projSolid) {
		super(x, y, sprite);
		animSprite = new AnimatedSprite(sheet, 16, 16, 3);
		animSprite.setFrameRate(rate);
		this.solid = solid;
		this.projSolid = projSolid;
	}
	
	public void update() {
		animSprite.update();
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int)x, (int)y, animSprite.getSprite(), true);
	}

}
