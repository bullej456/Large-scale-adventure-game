package com.entity.object;

import com.Main.Screen;
import com.entity.Entity;
import com.entity.mob.Player;
import com.graphics.Sprite;

public class Object extends Entity{
	
	protected Sprite sprite;
	protected boolean solid, projSolid;
	protected boolean onTop;
	protected boolean activated;
	
	public Object(int x, int y, Sprite sprite) {
		this.x = x << 4;
		this.y = y << 4;
		this.sprite = sprite;
		projSolid = solid = false;
		onTop = false;
	}
	
	public void set(boolean activate) {
		
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int)x, (int)y, sprite, true);
	}
	
	public boolean solid() {
		return solid;
	}
	
	public boolean projSolid() {
		return projSolid;
	}
	
	public boolean onTop() {
		return onTop;
	}
	
	protected void check() {
		
	}
	
	public boolean pressable() {
		return false;
	}

}
