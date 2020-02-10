package com.entity.spawner;

import com.Main.Main;
import com.Main.Screen;
import com.entity.mob.Chaser;
import com.entity.mob.Ghost;
import com.entity.mob.Goblin;
import com.graphics.AnimatedSprite;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.level.Level;

public class ChaserSpawner extends Spawner{
	
	protected Chaser.Type type;
	protected AnimatedSprite animSprite;

	public ChaserSpawner(int x, int y, int amount, Level level, Chaser.Type type) {
		super(x, y, Type.MOB, amount, level);
		sprite = Sprite.spawner;
		animSprite = new AnimatedSprite(SpriteSheet.spawn, 16, 16, 3);
		this.type = type;
		for(int i = 0; i < amount; i++) {
			Chaser chaser;
			
			int xx, yy;
			do{
				xx = ((int)x>>4) + random.nextInt(3)-1;
				yy = ((int)y>>4) + random.nextInt(3)-1;
			}
			while(xx == (int)x>>4 && yy == (int)y>>4);
			
			if(type == Chaser.Type.GHOST) chaser = new Ghost(x, y, 20);
			else if(type == Chaser.Type.GOBLIN) chaser = new Goblin(x, y, 20);
			else chaser = new Chaser(x, y, 20);
			
			level.add(chaser);
			entities.add(chaser);
			
			entities.get(i).remove();
		}
	}
	
	public void update() {
		animSprite.update();
		for(int i = 0; i < amount; i++) {
			if(entities.get(i).isRemoved()) {
				entities.remove(entities.get(i));
				Chaser chaser;
				
				int xx, yy;
				do{
					xx = ((int)x>>4) + random.nextInt(3)-1;
					yy = ((int)y>>4) + random.nextInt(3)-1;
				}
				while(xx == (int)x>>4 && yy == (int)y>>4);
				
				if(type == Chaser.Type.GHOST) chaser = new Ghost(xx, yy, 20);
				else if(type == Chaser.Type.GOBLIN) chaser = new Goblin(xx, yy, 20);
				else chaser = new Chaser(xx, yy, 20);
				
				level.add(chaser);
				entities.add(chaser);
				Main.musicPlayer.playSound(14, -5f-1.5f*(float)level.calcDist(level.getPlayer().getTileX(), level.getPlayer().getTileY(), xx, yy));
			}
		}
	}
	
	public void render(Screen screen) {
		if(sprite != null) screen.renderSprite((int)x, (int)y, sprite, true);
		for(int i = -1; i < 2; i++) {
			for(int j = -1; j < 2; j++) {
				if(i == 0 && j == 0) continue;
				screen.renderSprite((int)x + i*16, (int)y + j*16, animSprite.getSprite(), true);
			}
		}
	}
	
	public boolean solid() {
		return true;
	}
	
	public boolean projSolid() {
		return true;
	}

}
