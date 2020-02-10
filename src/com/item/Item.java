package com.item;

import com.Main.Main;
import com.Main.Screen;
import com.entity.Entity;
import com.entity.mob.Mob;
import com.entity.mob.Mob.Dir;
import com.entity.mob.Player;
import com.graphics.AnimatedSprite;
import com.graphics.Sprite;

public class Item extends Entity{
	
	protected Sprite sprite;
	protected boolean using = false;
	protected int damage = 0;
	protected Mob mob;
	protected Dir dir;
	protected int sizeX = 16, sizeY = 16;
	
	protected int floatCounter = 0;
	protected boolean floatUp = true;
	protected boolean held;
	protected boolean pickable = false;
	protected int time = 0;
	
	protected AnimatedSprite up;
	protected AnimatedSprite down;
	protected AnimatedSprite left;
	protected AnimatedSprite right;
	protected AnimatedSprite animSprite = null;
	
	protected boolean despawn = false;
	protected int despawnTimer = 0;
	protected int despawnTime = 300;
	
	protected Item() {
		held = false;
		dir = Dir.UP;
		x = 0;
		y = 0;
	}
	
	public Item(Mob mob, AnimatedSprite up, AnimatedSprite right, AnimatedSprite down, AnimatedSprite left) {
		this.mob = mob;
		this.up = up;
		this.right = right;
		this.down = down;
		this.left = left;
		held = true;
		animSprite = up;
		dir = Dir.UP;
		x = mob.getX();
		y = mob.getY();
	}
	
	public void update() {
		if(held) {
			if(dir != mob.getDir()) {
				if(mob.getDir() == Dir.UP) animSprite = up;
				else if(mob.getDir() == Dir.RIGHT) animSprite = right;
				else if(mob.getDir() == Dir.DOWN) animSprite = down;
				else if(mob.getDir() == Dir.LEFT) animSprite = left;
				dir = mob.getDir();
			}
			if(using) animSprite.update();
			else animSprite.setFrame(0);
			x = mob.getX();
			y = mob.getY();
		}
		else {
			if(floatCounter > 30 && floatUp) floatUp = false;
			if(floatCounter < 0 && !floatUp) floatUp = true;
			if(floatUp) {
				y -= 0.15f;
				floatCounter++;
			}
			else{
				y += 0.15f;
				floatCounter--;
			}
			time++;
			if(time >= 60) pickable = true;
			
			despawnUpdate();
		}
	}
	
	protected void despawnUpdate() {
		
	}
	
	public boolean isPickable() {
		return pickable;
	}
	
	public void drop() {
		held = false;
		pickable = false;
		time = 0;
	}
	
	public void pickup(Mob mob) {
		//if(level != null) level.add(new FloatFont((int)x>>4, (int)y>>4, "test", 60, 0xffffff));
		held = true;
		this.mob = mob;
	}
	
	public void setUse(boolean using) {
		this.using = using;
	}

	public void render(Screen screen) {
		if(held) {
			if(mob instanceof Player) 
				screen.renderSprite(Main.data.width/2 - sizeX/2, Main.data.height/2 - sizeY/2, animSprite.getSprite(), false);
			else
				screen.renderSprite((int)mob.getX() - sizeX/2, (int) mob.getY() - sizeY/2, animSprite.getSprite(), true);
		}
		else {
			screen.renderSprite((int)x, (int)y, animSprite.getSprite(), true);
		}
	}
	
	public boolean despawned() {
		return despawn;
	}
	
	public Sprite getSprite() {
		return right.getSprite();
	}
}
