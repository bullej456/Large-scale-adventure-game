package com.UI;

import java.awt.Color;
import java.awt.Graphics;

import com.Main.Vector2i;

public class UIProgressBar extends UIComponent{
	
	private float progress;
	public Color backColour;

	public UIProgressBar(Vector2i pos, Vector2i size) {
		super(pos);
		this.size = size;
		backColour = new Color(0xff000000);
		progress = 0.5f;
	}
	
	public void setProgress(float progress) {
		if(progress < 0) this.progress = 0;
		else if(progress > 1.0f) this.progress = 1.0f;
		else this.progress = progress;
	}
	
	public float getProgress() {
		return progress;
	}
	
	public void render(Graphics g) {
		g.setColor(backColour);
		g.fillRect(pos.x + offset.x, pos.y + offset.y, size.x, size.y);
		g.setColor(colour);
		g.fillRect(pos.x + offset.x, pos.y + offset.y, (int) (size.x*progress), size.y);
	}

}
