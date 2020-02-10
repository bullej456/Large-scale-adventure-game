package com.entity;

import com.Main.Screen;
import com.graphics.Sprite;

public class Particle extends Entity{
	
	protected Sprite sprite;
	protected int life;
	protected int time = 0;
	
	protected double xx, yy, zz;
	protected double xa, ya, za;
	
	public Particle(int x, int y, int life, boolean blood) {
		this.life = life + (random.nextInt(20) - 10);
		if(!blood) sprite = Sprite.particle;
		else sprite = Sprite.blood;
		this.x = x;
		this.y = y;
		xx = x;
		yy = y;
		
		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		if(!blood) this.zz = random.nextFloat() + 2.0;
		else this.zz = 0f;
	}
	
	public void update() {
		time++;
		if(time >= 7400) time = 0;
		if(time > life) remove();
		za -= 0.1;
		
		if(zz < 0) {
			zz = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}
		
		move(xx + xa, (yy + ya) + (zz + za));
	}
	
	protected void move(double x, double y) {
		if(collision(x, y)) {
			xa *= -0.5;
			ya *= -0.5;
			za *= -0.5;
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}
	
	public boolean collision(double x, double y) {
		boolean solid = false;
		//Checks all corners of tiles in front, not just tile in front of origin.
		for(int c = 0; c < 4; c++) {
			double xt = (x - c%2 * 16)/16;
			double yt = ((y-0) - c/2 * 16)/16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if(c%2 == 0) ix = (int) Math.floor(xt);
			if(c/2 == 0) iy = (int) Math.floor(yt);
			if(level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}
	
	public void render(Screen screen) {
		screen.renderSprite((int) xx - 1, (int) yy - (int) zz - 1, sprite, true);
	}

}
