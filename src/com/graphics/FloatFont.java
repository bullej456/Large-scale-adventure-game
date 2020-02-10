package com.graphics;

import com.Main.Screen;
import com.entity.Entity;

public class FloatFont extends Entity{
	
	private Font font = new Font();
	private int color = 0xffffff00;
	private boolean floating = true;
	public String text;
	private int time = 0, maxTime, offset = 0;
	
	public FloatFont(int x, int y, String text, int maxTime) {
		this.x = x << 4;
		this.y = y << 4;
		this.text = text;
		this.maxTime = maxTime;
		font.setFixed(true);
	}
	
	public FloatFont(int x, int y, String text, int maxTime, int color) {
		this.color = color;
		this.x = x << 4;
		this.y = y << 4;
		this.text = text;
		this.maxTime = maxTime;
		font.setFixed(true);
	}
	
	public void update() {
		if(floating) {
			offset += 1;
			time++;
			if(time >= maxTime) {
				floating = false;
				remove();
			}
		}
	}
	
	public boolean isFloating() {
		return floating;
	}
	
	public void floatFont() {
		floating = true;
		offset = 0;
		time = 0;
	}

	public void render(Screen screen) {
		if(floating) font.render((int)x - text.length()*2, (int)y - offset, text, screen, color);
	}

}
