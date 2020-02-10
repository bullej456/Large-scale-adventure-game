package com.entity;

import com.graphics.Sprite;

public class BlastParticle extends Particle{

	public BlastParticle(int x, int y, int life, boolean blood) {
		super(x, y, life, blood);
		this.life = life;
		zz = 0;
		sprite = Sprite.particleYellow;
		za *= 2;
		xa *= 2;
	}
	
	public void update() {
		time++;
		if(time >= 7400) time = 0;
		if(time > life) remove();
		//za -= 0.1;
		
		if(zz < 0) {
			zz = 0;
			//za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}
		
		move(xx + xa, (yy + ya) + (zz + za));
	}
	
	protected void move(double x, double y) {
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

}
