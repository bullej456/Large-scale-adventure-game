package com.entity.object.active;

import com.Main.Main;
import com.entity.mob.Mob.Dir;
import com.entity.mob.Player;
import com.entity.object.ActiveObject;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;

public class PushableObject extends ActiveObject{
	
	protected int counter = 0;
	protected int xo, yo;
	protected Dir dir = Dir.RIGHT;

	public PushableObject(int x, int y) {
		super(x, y, Sprite.boulder, SpriteSheet.boulder, 10);
		projSolid = true;
		range = 2;
	}
	
	protected void doThing() {
		Player p = level.getPlayer();
		float f = 0.5f;
		
		if(counter == 0) {
			xo = (int) x;
			yo = (int) y;
			if(p.getDir() == Dir.RIGHT && p.getX() < x+7 && !(collision2(getTileX() + 1, getTileY()))) {
				xo = (int) (x + 16);
				dir = Dir.RIGHT;
			}
			else if(p.getDir() == Dir.LEFT && p.getX() > x+7 && !(collision2(getTileX() - 1, getTileY()))) {
				xo = (int) (x - 16);
				dir = Dir.LEFT;
			}
			else if(p.getDir() == Dir.DOWN && p.getY() < y+7 && !(collision2(getTileX(), getTileY() + 1))) {
				yo = (int) (y + 16);
				dir = Dir.DOWN;
			}
			else if(p.getY() > y+7 && !(collision2(getTileX(), getTileY() - 1))) {
				yo = (int) (y - 16);
				dir = Dir.UP;
			}
			if(xo != x || yo != y) Main.musicPlayer.playSound(12, -10f);
		}
		
		if(xo != x || yo != y) {
			if(dir == Dir.RIGHT) x += f;
			else if(dir == Dir.LEFT) x -= f;
			else if(dir == Dir.DOWN) y += f;
			else if(dir == Dir.UP) y -= f;
		}

		pressable = false;
		counter++;
		if(counter >= (int)(16/f)) {
			counter = 0;
			activated = false;
			pressable = true;
			x = xo;
			y = yo;
		}
		
		animSprite.setFrame(0);
	}

}
