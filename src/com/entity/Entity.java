package com.entity;

import java.util.Random;

import com.Main.Screen;
import com.entity.mob.Mob;
import com.level.Level;

public class Entity {
	
	protected double x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();
	
	public void update() {
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public int getTileX() {
		return (int)x >> 4;
	}
	
	public int getTileY() {
		return (int)y >> 4;
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean projSolid() {
		return false;
	}
	
	public boolean onTop() {
		return false;
	}

	public boolean pressable() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected boolean collision2(int xTile, int yTile) {
		boolean solid = false;
		if(level.getTile(xTile, yTile).solid()) solid = true;
		
		for(Entity e: level.getEntities()) {
			if(e.equals(this)) continue;
			if(e instanceof Mob) {
				//FILL...
			}
			if(e.getTileX() == xTile && e.getTileY() == yTile) {
				if(e.solid()) {
					solid = true;
					break;
				}
			}
		}
		return solid;
	}

}
