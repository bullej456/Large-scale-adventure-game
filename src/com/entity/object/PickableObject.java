package com.entity.object;

import com.Main.InventorySlot.Ingredient;
import com.Main.Main;
import com.Main.Screen;
import com.entity.mob.Player;
import com.graphics.FloatFont;
import com.graphics.Sprite;

public class PickableObject extends Object{
	
	protected Sprite currentSprite, sprite2;
	protected boolean grown = true;
	protected int growTime, time = 0;

	protected boolean pressable = false;
	protected boolean holder = false;
	private boolean canBePressed = false;
	
	private Ingredient i;
	
	private String text;

	public PickableObject(int x, int y, Sprite sprite, Sprite sprite2, int growTime, String text, Ingredient i) {
		super(x, y, sprite);
		currentSprite = sprite;
		this.sprite2 = sprite2;
		this.growTime = growTime;
		this.i = i;
		solid = true;
		this.text = text;
	}
	
	public void update() {
		check();
		if(!grown) time++;
		if(time >= growTime) {
			time = 0;
			grown = true;
		}
	}
	
	protected void check() {
		Player player = level.getPlayer();
		if(holder =! player.input.e && grown) pressable = true;
		holder = player.input.e;
		double dx = Math.abs(x/16 - player.getTileX());
		double dy = Math.abs(y/16 - player.getTileY());
		double distance = Math.sqrt(Math.abs(dx*dx) + Math.abs(dy*dy));
		if(distance < 1.2 && pressable) {
			canBePressed = true;
			if(grown && player.input.e) {
				Main.musicPlayer.playSound(7, -10f);
				if(i == Ingredient.GARLIC) Main.data.garlic++;
				else if(i == Ingredient.STRAWBERRY) Main.data.strawberry++;
				else if(i == Ingredient.PFLOWER) Main.data.pflower++;
				else if(i == Ingredient.RFLOWER) Main.data.rflower++;
				level.add(new FloatFont((int)x>>4, (int)y>>4, text, 40));
				grown = false;
				pressable = false;
			}
		}
		else canBePressed = false;
	}
	
	public boolean pressable() {
		return canBePressed;
	}

	public void render(Screen screen) {
		if(grown) currentSprite = sprite;
		else currentSprite = sprite2;
		screen.renderSprite((int)x, (int)y, currentSprite, true);
	}
}
