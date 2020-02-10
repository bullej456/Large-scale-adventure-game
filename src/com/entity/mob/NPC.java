package com.entity.mob;

import com.Main.Main;
import com.graphics.AnimatedSprite;
import com.graphics.QuestionBox;
import com.graphics.SpriteSheet;

public class NPC extends Dummy{
	
	protected QuestionBox qb;

	protected boolean pressable = false;
	protected boolean holder = false;
	protected boolean canBePressed = false;
	protected boolean activated = false;
	protected int wonder = 48;
	protected double xo, yo;

	public NPC(int x, int y, QuestionBox qb) {
		super(x, y, 1000);
		up = new AnimatedSprite(SpriteSheet.dummyU, 16, 16, 4);
		down = new AnimatedSprite(SpriteSheet.dummyD, 16, 16, 4);
		left = new AnimatedSprite(SpriteSheet.dummyL, 16, 16, 4);
		right = new AnimatedSprite(SpriteSheet.dummyR, 16, 16, 4);
		animSprite = down;
		this.qb = qb;
		xo = this.x;
		yo = this.y;
	}
	
	public NPC(int x, int y, QuestionBox qb, int wonder) {
		super(x, y, 1000);
		up = new AnimatedSprite(SpriteSheet.dummyU, 16, 16, 4);
		down = new AnimatedSprite(SpriteSheet.dummyD, 16, 16, 4);
		left = new AnimatedSprite(SpriteSheet.dummyL, 16, 16, 4);
		right = new AnimatedSprite(SpriteSheet.dummyR, 16, 16, 4);
		animSprite = down;
		this.qb = qb;
		xo = this.x;
		yo = this.y;
		this.wonder = wonder;
	}
	
	public void update() {
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
		
		check();
		qb.update();
	}
	
	protected void move() {
		time++;
		if(time >= changeTime) {
			time = 0;
			changeTime = random.nextInt(20) + 40;
			//Below removes diagonal movement.
			do {
				xa = random.nextInt(3) - 1;
				ya = random.nextInt(3) - 1;
			} while(xa != 0 && ya != 0);
			
			if(x - xo > wonder && x - xo >= 0) {
				xa = -1;
				ya = 0;
			}
			if(x - xo < -wonder && x - xo <= 0) {
				xa = 1;
				ya = 0;
			}
			if(y - yo > wonder && y - yo >= 0) {
				ya = -1;
				xa = 0;
			}
			if(y - yo < -wonder && y - yo <= 0) {
				ya = 1;
				xa = 0;
			}
			if(random.nextInt(2) == 0) xa = ya = 0;
			xa *= speed;
			ya *= speed;
		}
		if(activated) xa = ya = 0;
	}
	
	protected void check() {
		Player player = level.getPlayer();
		if(holder =! player.input.e) pressable = true;
		holder = player.input.e;
		double dx = Math.abs((x+8)/16 - player.getTileX());
		double dy = Math.abs((y+8)/16 - player.getTileY());
		double distance = Math.sqrt(Math.abs(dx*dx) + Math.abs(dy*dy));
		
		if(distance < 1.2 && pressable) {
			canBePressed = true;
			if( !activated && player.input.e) {
				activated = true;
				pressable = false;
				
				qb.tbs.get(0).show(true);
				Main.musicPlayer.playSound(16, -10f);
			}
		}
		else canBePressed = false;
		
		if(distance >= 1.5 && activated) {
			activated = false;
			
			qb.tbs.get(0).show(false);
		}
	}
	
	public boolean pressable() {
		return canBePressed;
	}

}
