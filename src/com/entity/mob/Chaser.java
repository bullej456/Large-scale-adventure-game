package com.entity.mob;

import java.util.ArrayList;

import com.Main.Main;
import com.Main.Screen;
import com.Main.Vector2i;
import com.graphics.AnimatedSprite;
import com.graphics.Font;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.level.Node;

public class Chaser extends Mob{
	
	public enum Type{
		NONE, GHOST, GOBLIN;
	}
	
	protected Font exclamation = new Font();
	protected boolean seenPlayer = false;
	protected int exTime = 0;

	protected AnimatedSprite up = new AnimatedSprite(SpriteSheet.ghostU, 16, 16, 4);
	protected AnimatedSprite down = new AnimatedSprite(SpriteSheet.ghostD, 16, 16, 4);
	protected AnimatedSprite left = new AnimatedSprite(SpriteSheet.ghostL, 16, 16, 4);
	protected AnimatedSprite right = new AnimatedSprite(SpriteSheet.ghostR, 16, 16, 4);
	protected AnimatedSprite animSprite = null;
	
	protected int time = 0;
	protected int changeTime = 60;
	protected double xa = 0;
	protected double ya = 0;
	protected boolean chase = false;
	protected int range = 100;
	protected double speed = 0.8;
	protected int attackDamage = 10;
	
	protected boolean justHit = false;
	protected int hitTime = 0;
	protected int hitTimeCap = 40;
	protected int hitRange = 12;
	
	private ArrayList<Node> path = null;
	private int pathUpdateTime = 0;
	
	public Chaser(int x, int y, int despawnRange) {
		this.x = (x << 4);
		this.y = (y << 4);
		sprite = Sprite.playerD;
		animSprite = down;
		xO = this.x;
		yO = this.y;
		this.despawnRange = despawnRange;
		down.setFrameRate(15);
		up.setFrameRate(15);
		left.setFrameRate(15);
		right.setFrameRate(15);
		exclamation.setFixed(true);
	}

	@Override
	public void update() {
		if(hit) checkHit();
		
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
		
		if(walking) animSprite.update();
		else animSprite.setFrame(0);

		//ADDED
		walking = true;
		
		attack();
	}
	
	private void attack() {
		Player player = level.getPlayer();
		int dx = Math.abs((int)player.getX() - (int)x);
		int dy = Math.abs((int)player.getY() - (int)y);
		double distance = Math.sqrt((dx*dx)*(dy*dy));
		if(distance <= hitRange && distance >= 1 && !justHit) {
			level.getPlayer().setHit(attackDamage);
			justHit = true;
		}
		
		if(justHit) {
			hitTime++;
			if(hitTime >= hitTimeCap) {
				hitTime = 0;
				justHit = false;
			}
		}
	}
	
	/////
	private void as() {
		pathUpdateTime++;
		int px = (int)level.getPlayer().getX();
		int py = (int)level.getPlayer().getY();
		Vector2i start = new Vector2i((int)(x) >> 4, (int)(y) >> 4);
		Vector2i goal = new Vector2i((int)(px >> 4), (int)(py >> 4));
		if(pathUpdateTime >= 5) {
			path = level.findPath(start, goal);
			pathUpdateTime = 0;
		}
		if(path != null) {
			if(path.size() > 0) {
				Vector2i vec = path.get(path.size() - 1).tile;
//				System.out.println(vec.x + ", " + x);
				if((int)x - (vec.x << 4) < 0) xa += 1;
				else if((int)x - (vec.x << 4) > 0) xa -= 1;
				if((int)y < (vec.y << 4)) ya += 1;
				else if((int)y > (vec.y << 4)) ya -= 1;
//				System.out.println(xa + ", " + ya);
			}
		}
	}
	/////
	
	private void move() {
		Player player = level.getPlayer();
		int dx = Math.abs((int)player.getX() - (int)x);
		int dy = Math.abs((int)player.getY() - (int)y);
		double distance = Math.sqrt((dx*dx)+(dy*dy));
		if(distance <= range && distance >= 1) chase = true;
		else chase = false;
		
		if(chase) {
			exTime++;
			if(!seenPlayer && exTime < 40) {
				seenPlayer = true;
				Main.musicPlayer.playSound(17, -12f-1.5f*(float)level.calcDist(level.getPlayer().getTileX(),
						level.getPlayer().getTileY(), (int)x>>4, (int)y>>4));
			}
			if(exTime >= 40) seenPlayer = false;
		}
		else {
			if(seenPlayer) seenPlayer = false;
			if(exTime >= 40) exTime = 0;
		}
		
		if(chase) {
			xa = 0;
			ya = 0;
			as();
			
			if(dx < 17 && dy < 17) {
				if(x < player.getX() && dx > 2) xa = 1.3;
				else if(x > player.getX() && dx > 2) xa = -1.3;
				if(y < player.getY() && dy > 2) ya = 1.3;
				else if(y > player.getY() && dy > 2) ya = -1.3;
			}
			xa *= speed;
			ya *= speed;
		}
		else {
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
	}
	
	@Override
	public void render(Screen screen) {
		double xx = x - (Sprite.playerD.SIZE/2);
		double yy = y - (Sprite.playerD.SIZE/2);
		sprite = animSprite.getSprite();
		screen.renderMob((int)x, (int)y, sprite);
		if(seenPlayer) exclamation.render((int)x + 4, (int)y - 8, "!", screen, 0xffff00);
	}

}
