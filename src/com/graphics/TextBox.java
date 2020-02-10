package com.graphics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.Main.Main;
import com.Main.Vector2i;
import com.UI.UILabel;
import com.UI.UIManager;
import com.UI.UIPanel;
import com.entity.Entity;

public class TextBox extends Entity{
	
		public int sizeX, sizeY;
	
		private String text;
		private UIManager ui;
		UIPanel panel, panel1;
		
		private int lines = 1;
		private int len, lineLength;
		private List<String> t = new ArrayList<String>();
	
		public TextBox(int x, int y, int sizeX, int sizeY, String text) {
			this.x = x;
			this.y = y;
			this.text = text;
			this.sizeX = sizeX;
			this.sizeY = sizeY;
			
			checkString();
			ui = Main.getUIManager();
			
			this.sizeY = sizeY*lines + Main.getWindowHeight()/40;

			this.sizeX = 4*Main.getWindowHeight()/15;
			double a = Main.getWindowHeight()/50;
			panel = new UIPanel(new Vector2i(x - (int)(a/2f), y - (int)(a/2f)), new Vector2i(this.sizeX + (int)a, this.sizeY + (int)a), true);
			panel1 = new UIPanel(new Vector2i((int)(a/2f), (int)(a/2f)), new Vector2i(this.sizeX, this.sizeY), true);
			panel1.colour = new Color(0xffffff);
			for(int i = 0; i < t.size(); i++) {
				UILabel label = new UILabel(new Vector2i(Main.getWindowHeight()/60, (Main.getWindowHeight()/40)*(i+1)), t.get(i));
				label.colour = new Color(0, 0, 0);
				panel1.addComponent(label);
			}
			panel.addComponent(panel1);
			ui.addPanel(panel);
		}
		
		private void checkString() {
			len = text.length();
			lineLength = 20;
			lines = (int) Math.ceil(len/(float)lineLength);

			int index = -1;
			int start = 0;
			for(int i = 0; i < lines; i++) {
				if(i == lines-1) {
					for(int j = text.length(); j > i*lineLength; j--) {
						if(text.charAt(j-1) == ' ') {
							//index = j-1;
							break;
						}
						//else index = text.length();
					}
					//t.add(text.substring(i*lineLength, text.length()));
					t.add(text.substring(index+1, text.length()));
				}
				else{
					for(int j = lineLength*(i+1); j > i*lineLength; j--) {
						if(text.charAt(j-1) == ' ') {
							index = j-1;
							break;
						}
						else index = lineLength*(i+1);
					}
					t.add(text.substring(start, index));
					start = index+1;
				}
			}
		}
		
		public void setText(String text) {
			if(!(panel1.getComponents().get(0) instanceof UILabel)) {
				System.err.println("Trying to asign a string to something that is not a label!");
				return;
			}
			this.text = text;
			t.clear();
			checkString();
			panel1.getComponents().clear();
			for(int i = 0; i < t.size(); i++) {
				UILabel label = new UILabel(new Vector2i(Main.getWindowHeight()/60, (Main.getWindowHeight()/40)*(i+1)), t.get(i));
				label.colour = new Color(0, 0, 0);
				panel1.addComponent(label);
			}
		}
		
		public void show(boolean show) {
			if(show) panel.show = true;
			else panel.show = false;
		}
		
		public boolean isShowing() {
			return panel.show;
		}
		
}
