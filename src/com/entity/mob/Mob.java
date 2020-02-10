package com.entity.mob;

import com.Main.Screen;
import com.entity.Entity;
import com.entity.projectile.Bullet;
import com.entity.projectile.Projectile;
import com.entity.projectile.Spell;
import com.entity.projectile.Swoop;
import com.entity.spawner.ParticleSpawner;
import com.graphics.FloatFont;
import com.graphics.Sprite;
import com.item.HandItem;
import com.item.HeartItem;
import com.item.Item;
import com.item.StickItem;
import com.item.SwordItem;

public abstract class Mob extends Entity {
	
	protected Sprite sprite;
	protected Dir dir = Dir.UP;
	protected boolean moving = false;
	protected boolean walking = false;
	public int health = 100, maxHealth = 100;
	protected int fireRate = 0;
	
	protected boolean hit = false;
	protected int hitDamage = 0;
	protected Item item;
	
	protected double xO, yO;
	protected int despawnRange;

	public enum Dir{
		UP, DOWN, LEFT, RIGHT;
	}
	
	public void move(double xa, double ya) {
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa > 0) dir = Dir.RIGHT;
		if(xa < 0) dir = Dir.LEFT;
		if(ya > 0) dir = Dir.DOWN;
		if(ya < 0) dir = Dir.UP;
		
		while(xa != 0) {
			if(Math.abs(xa) > 1) {
				if(!collision(abs(xa), ya)) this.x += abs(xa);
				xa -= abs(xa);
			}
			else {
				if(!collision(abs(xa), ya)) this.x += xa;
				xa = 0;
			}
		}
		while(ya != 0) {
			if(Math.abs(ya) > 1) {
				if(!collision(xa, abs(ya))) this.y += abs(ya);
				ya -= abs(ya);
			}
			else {
				if(!collision(xa, abs(ya))) this.y += ya;
				ya = 0;
			}
		}

		if((Math.abs(level.getPlayer().getTileX() - x/16) > 14 || Math.abs(level.getPlayer().getTileY() - y/16) > 14)
				&& (despawnRange < Math.abs(x - xO)/16 || despawnRange < Math.abs(y - yO)/16)) {
			//remove();
			x = xO;
			y = yO;
		}
	}
	
	public boolean isHolding() {
		if(item == null) return false;
		else return true;
	}
	
	private int abs(double value) {
		if(value < 0) return -1;
		else return 1;
	}
	
	protected void shoot(double x, double y, double dir) {
		Projectile p = null;
		if(item instanceof StickItem) p = new Spell(x, y, dir, this);
		if(item instanceof HandItem) p = new Bullet(x, y, dir, this);
		if(item instanceof SwordItem) p = new Swoop(x, y, dir, this);

		if(p != null) fireRate = p.FIRE_RATE;
		if(p != null) level.add(p);
	}
	
	public void setHit(int hitDamage) {
		hit = true;
		this.hitDamage = hitDamage;
	}
	
	protected void checkHit() {
		health -= hitDamage;
		level.add(new FloatFont(((int)(x + random.nextInt(6)-3 + 8) >> 4), ((int)(y + 6) >> 4), "" + hitDamage, 40));
		level.add(new ParticleSpawner((int)x+8, (int)y+8, 240, 8, level, true));
		hit = false;
		hitDamage = 0;
		if(health <= 0) dead();
	}
	
	protected void dead() {
		if(random.nextInt(4) == 0) level.add(new HeartItem((int)x>>4, (int)y>>4));
		remove();
	}
	
	public abstract void update();
	
	public abstract void render(Screen screen);
	
	protected boolean collision(double xa, double ya) {
		boolean solid = false;
		//Checks all corners of tiles in front, not just tile in front of origin.
		for(int c = 0; c < 4; c++) {
			int xt = (int) (((x + xa) + c%2*15 - 0)/16);
			int yt = (int) (((y + ya) + c/2*15 - 0)/16);
			
			if(level.getTile(xt, yt).solid()) solid = true;
			
			for(Entity e: level.getEntities()) {
				if(e.equals(this)) break;
				if(e instanceof Mob) {
					//FILL...
				}
				//else {
					if(e.getTileX() == xt && e.getTileY() == yt) {
						if(e.solid()) {
							solid = true;
							break;
						}
					}
				//}
			}
		}
		return solid;
	}
	
	public boolean solid() {
		return false;
	}
	
	public Dir getDir() {
		return dir;
	}

}
