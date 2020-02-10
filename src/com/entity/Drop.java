package com.entity;

import com.Main.Main;
import com.Main.Screen;
import com.graphics.Sprite;

public class Drop extends Entity{
	
	private Sprite sprite;
	public boolean onTop;
	private int fallAmount, fall = 0;
	private boolean on = true;
	private boolean render = true;
	
	public Drop(boolean onTop) {
		this.x = (Main.data.playerX/16 + random.nextInt(40) - 20) << 4;
		this.y = (Main.data.playerY/16 + random.nextInt(40) - 20) << 4;
		this.onTop = onTop;
		sprite = Sprite.particleBlue;
		fallAmount = random.nextInt(Main.getWindowHeight()/3 + 10);
	}
	
	public void update(int xx, int yy) {
		if(render) {
			y += 2;
			x -= 0.2f;
			fall += 2;
			if(fall > fallAmount) {
				fall = 0;
				fallAmount = random.nextInt(Main.getWindowHeight()/3 + 10);
				x = (xx + random.nextInt(40) - 20) << 4;
				y = (yy + random.nextInt(40) - 20) << 4;
				if(!on) {
					render = false;
				}
			}
		}
	}
	
	public void rainOn() {
		on = true;
		render = true;
	}
	
	public void rainOff() {
		on = false;
	}
	
	public void render(Screen screen) {
		if(render) screen.renderSprite((int)x, (int)y, sprite, true);
	}

}
