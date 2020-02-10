package com.entity.object.active;

import com.Main.Main;
import com.entity.object.ActiveObject;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.graphics.TextBox;
import com.item.HandItem;
import com.item.Item;
import com.item.StickItem;
import com.item.SwordItem;

public class ChestObject extends ActiveObject{
	
	protected int item = -1;
	protected boolean opened = false;
	protected TextBox tb;
	protected int textTime = 0;

	public ChestObject(int x, int y, int item) {
		super(x, y, Sprite.chestC, SpriteSheet.chestO, 1000);
		this.item = item;
		projSolid = true;
	}
	
	protected void doThing() {
		textTime++;
		if(textTime > 180) tb.show(false);
		if(!opened) {
			Main.musicPlayer.playSound(19, -10f);
			opened = true;
			Item itemHolder = null;
			if(item == 0) {
				itemHolder = new HandItem((int)x>>4, ((int)y>>4) + 1);
				tb = new TextBox(Main.getWindowWidth()/2 - Main.getWindowHeight()/8, Main.getWindowHeight()/4, 3*Main.getWindowHeight()/15, Main.getWindowHeight()/35,
						"Hurray!!!! You have discovered the ...BULLET GLOVES!");
			}
			else if(item == 1) {
				itemHolder = new SwordItem((int)x>>4, ((int)y>>4) + 1);
				tb = new TextBox(Main.getWindowWidth()/2 - Main.getWindowHeight()/8, Main.getWindowHeight()/4, 3*Main.getWindowHeight()/15, Main.getWindowHeight()/35,
						"Hurray!!!! You have discovered the ...BASIC SWORD!");
			}
			else if(item == 2) {
				itemHolder = new StickItem((int)x>>4, ((int)y>>4) + 1);
				tb = new TextBox(Main.getWindowWidth()/2 - Main.getWindowHeight()/8, Main.getWindowHeight()/4, 3*Main.getWindowHeight()/15, Main.getWindowHeight()/35,
						"Hurray!!!! You have discovered the ...BASIC WAND!");
			}
			level.add(itemHolder);
		}
	}

}
