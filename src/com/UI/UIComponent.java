package com.UI;

import java.awt.Color;
import java.awt.Graphics;

import com.Main.Vector2i;

public class UIComponent {
	
	public Vector2i pos, offset, size;
	public Color colour;
	
	public UIComponent(Vector2i pos) {
		this.pos = pos;
		offset = new Vector2i();
	}
	
	public UIComponent(Vector2i pos, Vector2i size) {
		this.pos = pos;
		this.size = size;
		offset = new Vector2i();
	}
	
	void setOffset(Vector2i offset) {
		this.offset = offset;
	}
	
	public void update() {
		
	}
	
	public void render(Graphics g) {
		
	}
	
	public Vector2i getAbPos() {
		return pos.joint(offset);
	}

}
