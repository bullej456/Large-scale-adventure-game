package com.entity.object;

import com.Main.Main;
import com.Main.Screen;
import com.entity.mob.Player;
import com.graphics.Sprite;
import com.graphics.TextBox;

public class ReadableObject extends Object{
	
	
	protected TextBox tBox;
	protected String text;
	protected boolean pressable = false;
	protected boolean holder = false;
	private boolean canBePressed = false;
	
	//protected UIManager uim;

	public ReadableObject(int x, int y, Sprite sprite, String text) {
		super(x, y, sprite);
		solid = true;
		activated = false;
		
		this.text = text;
		tBox = new TextBox(Main.getWindowWidth()/2 - 2*Main.getWindowHeight()/15, Main.getWindowHeight()/5,
				4*Main.getWindowHeight()/15, Main.getWindowHeight()/35, text);
		tBox.show(false);
	}
	
	public void update() {
		check();
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int)x, (int)y, sprite, true);
	}
	
	protected void check() {
		Player player = level.getPlayer();
		if(holder =! player.input.e) pressable = true;
		holder = player.input.e;
		double dx = Math.abs(x/16 - player.getTileX());
		double dy = Math.abs(y/16 - player.getTileY());
		double distance = Math.sqrt(Math.abs(dx*dx) + Math.abs(dy*dy));
		
		if(distance < 1.2 && pressable) {
			canBePressed = true;
			if( !activated && player.input.e) {
				activated = true;
				pressable = false;
				tBox.show(true);
			}
		}
		else canBePressed = false;
		
		if(distance >= 1.5 && activated) {
			tBox.show(false);
			activated = false;
		}
	}
	
	public boolean pressable() {
		return canBePressed;
	}

}
