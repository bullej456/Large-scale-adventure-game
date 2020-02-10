package com.entity.mob;

import com.Main.Screen;
import com.graphics.AnimatedSprite;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;

public class Dummy extends Mob{
	
	protected AnimatedSprite up = new AnimatedSprite(SpriteSheet.bFlyD, 16, 16, 4);
	protected AnimatedSprite down = new AnimatedSprite(SpriteSheet.bFlyD, 16, 16, 4);
	protected AnimatedSprite left = new AnimatedSprite(SpriteSheet.bFlyD, 16, 16, 4);
	protected AnimatedSprite right = new AnimatedSprite(SpriteSheet.bFlyD, 16, 16, 4);
	protected AnimatedSprite animSprite = null;
	
	protected int time = 0;
	protected int changeTime = 50;
	protected double xa = 0;
	protected double ya = 0;
	protected double speed = 0.8;
	
	public Dummy(int x, int y, int despawnRange) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.playerD;
		animSprite = down;
		xO = this.x;
		yO = this.y;
		this.despawnRange = despawnRange;
	}

	@Override
	public void update() {
		if(hit) checkHit();
		
		if(walking) animSprite.update();
		else animSprite.setFrame(0);
		
		move();
		if(ya < 0) {
			animSprite = up;
			dir = Dir.UP;
		}
		else if(ya > 0) {
			animSprite = down;
			dir = Dir.DOWN;
		}
		if(xa < 0) {
			animSprite = left;
			dir = Dir.LEFT;
		}
		else if(xa > 0) {
			animSprite = right;
			dir = Dir.RIGHT;
		}
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else walking = false;
	}
	
	protected void move() {
		time++;
		if(time % changeTime == 0) {
			time = 0;
			changeTime = random.nextInt(60) + 40;
			//Below removes diagonal movement.
			do {
				xa = random.nextInt(3) - 1;
				ya = random.nextInt(3) - 1;
			} while(xa != 0 && ya != 0);
			if(random.nextInt(3) == 0) xa = ya = 0;
			xa *= speed;
			ya *= speed;
		}
	}

	@Override
	public void render(Screen screen) {
		double xx = x - (Sprite.playerD.SIZE/2);
		double yy = y - (Sprite.playerD.SIZE/2);
		sprite = animSprite.getSprite();
		screen.renderMob((int)x, (int)y, sprite);
	}

}
