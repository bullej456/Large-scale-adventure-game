package com.entity.projectile;

import java.util.Random;

import com.Main.Main;
import com.Main.Screen;
import com.entity.Entity;
import com.entity.spawner.ParticleSpawner;
import com.graphics.Sprite;

public abstract class Projectile extends Entity{
	
	protected final double xOrigin, yOrigin;
	protected double angle;
	protected Sprite sprite;
	protected double nx, ny;
	protected double speed, range, damage;
	protected double x, y;
	protected double distance;
	protected boolean blast;
	protected Entity e;
	
	protected final static Random random = new Random();
	public int FIRE_RATE = 10;
	
	public Projectile(double x, double y, double dir, Entity e) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
		blast = false;
		this.e = e;
	}
	
	public void update() {
		//change last parameter input.
		if(level.projCollision(x, y, nx, ny, 4, (int) damage, blast, e) == 2) {
			level.add(new ParticleSpawner((int)x, (int)y, 15, level));
			Main.musicPlayer.playSound(9, -10f-1.5f*(float)level.calcDist(level.getPlayer().getTileX(), level.getPlayer().getTileY(), (int)x>>4, (int)y>>4));
			remove();
		}
		else if(level.projCollision(x, y, nx, ny, 4, (int) damage, blast, e) == 1) {
			level.add(new ParticleSpawner((int)x, (int)y, 15, level));
			Main.musicPlayer.playSound(10, -10f-1.5f*(float)level.calcDist(level.getPlayer().getTileX(), level.getPlayer().getTileY(), (int)x>>4, (int)y>>4));
			remove();
		}
		move();
	}
	
	protected void move() {
		x += nx;
		y += ny;
		if(distance() > range) remove();
	}
	
	protected double distance() {
		double dist;
		dist = Math.sqrt(Math.abs(xOrigin - x)*Math.abs(xOrigin - x) + 
				Math.abs(yOrigin - y)*Math.abs(yOrigin - y));
		return dist;
	}

	public int getDamage() {
		return (int) damage;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) x - 0, (int) y - 0, sprite, true);
	}
	
}
