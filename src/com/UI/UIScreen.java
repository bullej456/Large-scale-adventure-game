package com.UI;

import java.util.ArrayList;

import com.Main.Data;
import com.Main.Main;
import com.Main.Screen;
import com.entity.mob.Player;
import com.graphics.AnimatedSprite;
import com.graphics.Animation;
import com.graphics.Font;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;

public class UIScreen {
	
	private Data data = Main.data;
	private ArrayList<AnimatedSprite> hearts = new ArrayList<AnimatedSprite>();
	private ArrayList<AnimatedSprite> stams = new ArrayList<AnimatedSprite>();
	private AnimatedSprite xp;
	private Font level, one, two, three;
	private Animation glow = new Animation(new AnimatedSprite(SpriteSheet.glow, 16, 16, 4), 6, 60);
	private Player player;
	
	public UIScreen(Player player) {
		this.player = player;
		int n = (int) (data.maxHealth/20f);
		for(int i = 0; i < n; i++) {
			hearts.add(new AnimatedSprite(SpriteSheet.heart, 16, 16, 4));
		}
		
		n = (int) (data.maxStamina/20f);
		for(int i = 0; i < n; i++) {
			stams.add(new AnimatedSprite(SpriteSheet.stam, 16, 16, 4));
		}
		
		xp = new AnimatedSprite(SpriteSheet.xpJar, 16, 16, 5);
		level = new Font();
		one = new Font();
		two = new Font();
		three = new Font();
	}
	
	public void update() {
		int n = (int) (data.maxHealth/20f);
		int h = (int) (data.health/10f);
		for(int i = 0; i < n; i++) {
			if(h == i*2+1) hearts.get(i).setFrame(1);
			else if(h > i*2) hearts.get(i).setFrame(0);
			else hearts.get(i).setFrame(2);
		}
		
		n = (int) (data.maxStamina/20f);
		h = (int) (data.stamina/10f);
		for(int i = 0; i < n; i++) {
			if(h == i*2+1) stams.get(i).setFrame(1);
			else if(h > i*2) stams.get(i).setFrame(0);
			else stams.get(i).setFrame(2);
		}
		
		float f = 0;
		if(data.xp != 0) f = (data.xp/((float)data.nextXp));
		if(f < 0.2) xp.setFrame(0);
		else if(f < 0.4) xp.setFrame(1);
		else if(f < 0.6) xp.setFrame(2);
		else if(f < 0.8) xp.setFrame(3);
		else xp.setFrame(4);
		
		if(f >= 0.90 && glow.isEnd()) glow.reset();
		glow.update();
	}
	
	public void render(Screen screen) {
		int n = (int) (data.maxHealth/20f);
		for(int i = 0; i < n; i++) {
			screen.renderSprite((int)(i*data.width/40f) + (int)(data.width/15f), (int)(data.height/40f), hearts.get(i).getSprite(), false);
		}
		////////
		n = (int) (data.maxStamina/20f);
		for(int i = 0; i < n; i++) {
			screen.renderSprite((int)(i*data.width/27f) + (int)(data.width/15f), (int)(3*data.height/50f), stams.get(i).getSprite(), false);
		}
		////////
		if(glow.getSprite() != null) screen.renderSprite((int)(data.width/30f), (int)(17*data.height/20f), glow.getSprite(), false);
		screen.renderSprite((int)(data.width/30f), (int)(17*data.height/20f), xp.getSprite(), false);
		level.render((int)(data.width/24f), (int)(22*data.height/24f), 0x000000, "" + data.level, screen);
		////////
		if(data.zone == 5) screen.renderSprite((int)(data.width - 66), (int)(data.height - 66), Sprite.zone1, false);
		else if(data.zone == 4) screen.renderSprite((int)(data.width - 66), (int)(data.height - 66), Sprite.zone2, false);
		screen.renderSprite((int)(data.width - 66 + 1 + data.playerX/16), (int)(data.height - 66 + 1 + data.playerY/16),
				Sprite.particleRed, false);
		////////
		int x1 = (int)(1.2*data.width/30f);
		int y1 = (int)(18*data.height/20f);
		screen.renderSprite(11*x1, y1, Sprite.plaque, false);
		screen.renderSprite(12*x1, y1, Sprite.plaque, false);
		screen.renderSprite(13*x1, y1, Sprite.plaque, false);
		one.render(11*x1 + 2, y1 + 2, 0x000000, "1", screen);
		two.render(12*x1 + 2, y1 + 2, 0x000000, "2", screen);
		three.render(13*x1 + 2, y1 + 2, 0x000000, "3", screen);
		if(player.getItem(1) != null)
			screen.renderSprite(11*x1-4, y1, player.getItem(1).getSprite(), false);
		if(player.getItem(2) != null)
			screen.renderSprite(12*x1-4, y1, player.getItem(2).getSprite(), false);
		if(player.getItem(3) != null)
			screen.renderSprite(13*x1-4, y1, player.getItem(3).getSprite(), false);
	}

}
