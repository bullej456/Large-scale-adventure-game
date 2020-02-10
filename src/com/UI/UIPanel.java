package com.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import com.Main.Vector2i;

public class UIPanel extends UIComponent{

	private List<UIComponent> components = new ArrayList<UIComponent>();
	public boolean show;
	
	public UIPanel(Vector2i pos, Vector2i size, boolean show) {
		super(pos);
		this.size = size;
		this.show = show;
		colour = new Color(0xff322400);
	}
	
	public List<UIComponent> getComponents(){
		return components;
	}
	
	public void addComponent(UIComponent comp) {
		components.add(comp);
	}
	
	public void update() {
		if(show) {
			for(UIComponent c: components) {
				c.setOffset(pos.joint(offset));
				c.update();
			}
		}
	}
	
	public void render(Graphics g) {
		if(show) {
			g.setColor(colour);
			g.fillRect(pos.x + offset.x, pos.y + offset.y, size.x, size.y);
			for(UIComponent c: components) {
				c.render(g);
			}
		}
	}
	
	
}
