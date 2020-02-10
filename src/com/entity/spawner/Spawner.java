package com.entity.spawner;

import java.util.ArrayList;
import java.util.List;

import com.Main.Screen;
import com.entity.Entity;
import com.graphics.Sprite;
import com.level.Level;

public class Spawner extends Entity{
	
	protected List<Entity> entities = new ArrayList<Entity>();
	protected int amount;
	protected Sprite sprite;
	
	public enum Type {
		MOB, PARTICLE;
	}
	
	private Type type;
	
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x << 4;
		this.y = y << 4;
		this.type = type;
		this.amount = amount;
	}
	
	public void update() {
		
	}
	
	public void render(Screen screen) {
		if(sprite != null) screen.renderSprite((int)x, (int)y, sprite, true);
	}
	
}
