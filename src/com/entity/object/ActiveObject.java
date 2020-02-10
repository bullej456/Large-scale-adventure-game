package com.entity.object;

import com.Main.Screen;
import com.entity.mob.Player;
import com.graphics.AnimatedSprite;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;

public class ActiveObject extends Object{
	
	protected AnimatedSprite animSprite;
	protected boolean pressable = false;
	protected boolean holder = false;
	private boolean canBePressed = false;
	protected float range;

	public ActiveObject(int x, int y, Sprite sprite, SpriteSheet sheet, int rate) {
		super(x, y, sprite);
		solid = true;
		animSprite = new AnimatedSprite(sheet, 16, 16, 2);
		animSprite.setFrameRate(rate);
		activated = false;
		range = 1.2f;
	}
	
	public void activate() {
		activated = true;
	}
	
	public void update() {
		check();
		if(activated) {
			animSprite.update();
			doThing();
		}
	}
	
	protected void doThing() {
		
	}
	
	protected void check() {
		Player player = level.getPlayer();
		if(holder =! player.input.e) pressable = true;
		holder = player.input.e;
		double dx = Math.abs(x/16 - player.getTileX());
		double dy = Math.abs(y/16 - player.getTileY());
		double distance = Math.sqrt(Math.abs(dx*dx) + Math.abs(dy*dy));
		if(distance < range && pressable) {
			canBePressed = true;
			if( !activated && player.input.e) {
				activated = true;
				pressable = false;
			}
			else if(activated && player.input.e) {
				activated = false;
				pressable = false;
			}
		}
		else canBePressed = false;
	}
	
	public boolean pressable() {
		return canBePressed;
	}
	
	public void render(Screen screen) {
		Sprite currentSprite;
		if(activated) currentSprite = animSprite.getSprite();
		else currentSprite = sprite;
		screen.renderSprite((int)x, (int)y, currentSprite, true);
	}
}
