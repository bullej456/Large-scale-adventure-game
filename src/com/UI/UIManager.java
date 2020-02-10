package com.UI;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class UIManager {
	
	private List<UIPanel> panels = new ArrayList<UIPanel>();
	
	public UIManager() {
		
	}
	
	public void addPanel(UIPanel panel) {
		panels.add(panel);
	}
	
	public void update() {
		for(UIPanel p: panels) {
			p.update();
		}
	}
	
	public void render(Graphics g) {
		for(UIPanel p: panels) {
			p.render(g);
		}
	}
	
	public void remove(UIPanel panel) {
		panels.remove(panel);
	}

}
