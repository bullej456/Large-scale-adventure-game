package com.UI;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.Main.Keyboard;
import com.Main.Main;
import com.Main.Vector2i;

public class WorldMap {
	
	private ArrayList<UIButton> zones = new ArrayList<UIButton>();
	private UIPanel panel, panel2;
	private Keyboard input;
	private int W = Main.getWindowWidth();
	private int H = Main.getWindowHeight();
	private boolean mLast = false;
	
	public WorldMap(UIManager ui, Keyboard input){
		this.input = input;
		
		int w = 3, h = 3;
		for(int j = 0; j < h; j++) {
			for(int i = 1; i < w+1; i++) {
				int n = i + j*w;
				try {
					UIButton imageButton = new UIButton(new Vector2i((i-1)*H/11 + H/30, j*H/11 + H/30), ImageIO.read(new File("res/textures/levels/map" + n + ".png")),
							new UIActionListener() {
						public void perform() {
							System.out.println("zone" + n + "");
						}
					});
					zones.add(imageButton);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		panel2 = new UIPanel(new Vector2i(4*W/16, H/25), new Vector2i(H/15 + 9*H/11, H/15 + 9*H/11), true);
		panel = new UIPanel(new Vector2i(18, 18), new Vector2i(H/15 + 9*H/11 -H/20, H/15 + 9*H/11 -H/20), true);
		panel.colour = new Color(0,0,0);
		for(UIButton b: zones) {
			panel.addComponent(b);
		}
		panel2.addComponent(panel);
		ui.addPanel(panel2);
		panel2.show = false;
	}
	
	public void update() {
		if(!input.m && mLast) {
			if(panel2.show) Main.musicPlayer.playSound(3, -0f);
			else Main.musicPlayer.playSound(2, -0f);
			panel2.show = !(panel2.show);
		}
		mLast = input.m;
	}

}
