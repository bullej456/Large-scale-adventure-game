package com.Main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import com.UI.UIActionListener;
import com.UI.UIButton;
import com.UI.UIPanel;
import com.graphics.Sprite;

public class StartMenu {
	
	public boolean running = false;
	private ArrayList<UIButton> buttons = new ArrayList<UIButton>();
	private ArrayList<UIButton> buttons1 = new ArrayList<UIButton>();
	private int pageNum = 0;
	private UIPanel panel, panel1;

	public StartMenu() {
		running = true;
		panel = new UIPanel(new Vector2i(200*Main.data.scale, 130*Main.data.scale), new Vector2i(50*Main.data.scale, 42*Main.data.scale), false);
		buttons.add(new UIButton(new Vector2i(2*Main.data.scale, 2*Main.data.scale), new Vector2i(46*Main.data.scale, 10*Main.data.scale)
				, new Vector2i(-6*Main.data.scale, 2*Main.data.scale), new UIActionListener() {
			public void perform() {
				pageNum = 1;
				Main.musicPlayer.playSound(2, 0);
			}
		}));
		buttons.get(0).setText("Play");
		buttons.add(new UIButton(new Vector2i(2*Main.data.scale, 15*Main.data.scale), new Vector2i(46*Main.data.scale, 10*Main.data.scale)
				, new Vector2i(-11*Main.data.scale, 2*Main.data.scale), new UIActionListener() {
			public void perform() {
				System.out.println("Show settings page");
				Main.musicPlayer.playSound(2, 0);
			}
		}));
		buttons.get(1).setText("Settings");
		buttons.add(new UIButton(new Vector2i(2*Main.data.scale, 28*Main.data.scale), new Vector2i(46*Main.data.scale, 10*Main.data.scale)
				, new Vector2i(-6*Main.data.scale, 2*Main.data.scale), new UIActionListener() {
			public void perform() {
				System.exit(0);
				Main.musicPlayer.playSound(2, 0);
			}
		}));
		buttons.get(2).setText("Quit");
		for(UIButton b: buttons) panel.addComponent(b);
		Main.getUIManager().addPanel(panel);
		////////////
		buttons1.add(new UIButton(new Vector2i(4*Main.data.scale, 180*Main.data.scale), new Vector2i(46*Main.data.scale, 10*Main.data.scale)
				, new Vector2i(-6*Main.data.scale, 2*Main.data.scale), new UIActionListener() {
			public void perform() {
				pageNum = 0;
				Main.musicPlayer.playSound(3, 0);
			}
		}));
		buttons1.get(0).setText("Back");
		buttons1.add(new UIButton(new Vector2i(200*Main.data.scale, 180*Main.data.scale), new Vector2i(46*Main.data.scale, 10*Main.data.scale)
				, new Vector2i(-11*Main.data.scale, 2*Main.data.scale), new UIActionListener() {
			public void perform() {
				pageNum = -1;
				Main.musicPlayer.playSound(2, 0);
			}
		}));
		buttons1.get(1).setText("Use Slot");
		panel1 = new UIPanel(new Vector2i(100*Main.data.scale, 20*Main.data.scale), new Vector2i(250*Main.data.scale, 200*Main.data.scale), false);
		panel1.colour = new Color(30, 30, 30);
		
		for(UIButton b: buttons1) panel1.addComponent(b);
		for(int i = 0; i < 3; i++) {
			UIPanel holder = new UIPanel(new Vector2i(4*Main.data.scale, (4+i*55)*Main.data.scale), new Vector2i(242*Main.data.scale, 50*Main.data.scale), true);
			holder.colour = new Color(20, 20, 20);
			panel1.addComponent(holder);
		}
		Main.getUIManager().addPanel(panel1);
	}
	
	public void update() {
		if(!panel.show && pageNum == 0) {
			panel.show = true;
			panel1.show = false;
		}
		else if(!panel1.show && pageNum == 1) {
			panel1.show = true;
			panel.show = false;
		}
		else if(pageNum == -1) {
			panel.show = false;
			panel1.show = false;
			running = false;
		}
	}
	
	public void render(Screen screen) {
		if(pageNum == 0 || true) screen.renderSprite(0, -35, Sprite.statueBig, false);
	}
	
	public void renderG(Graphics g) {
		
	}
	
}
