package com.level;

import com.Main.Data;
import com.Main.Main;
import com.Main.Screen;
import com.UI.UIScreen;
import com.entity.mob.Player;

public class LevelManager {
	
	private Player player;
	private Data data;
	private Level level;
	private int s = 3;
	
	public LevelManager(Player player) {
		this.player = player;
		data = Main.data;
		
		if(data.zone == -1) {
			level = Level.map1zone4;
			this.player.init(level);
			level.initPlayer(this.player);
			data.zone = 4;
		}
		else {
			level = chooseLevel(data.zone);
			this.player.init(level);
			level.initPlayer(this.player);
		}
	}
	
	public void update() {
		player.update();
		level.update();
		boolean l, u, r, d;
		l = u = r = d = false;
		if(player.getTileX() == 0 && (player.getTileY() == 31 || player.getTileY() == 32)) l = true;
		else if(player.getTileX() == 64 && (player.getTileY() == 31 || player.getTileY() == 32)) r = true;
		else if(player.getTileY() == 0 && (player.getTileX() == 31 || player.getTileX() == 32)) u = true;
		else if(player.getTileY() == 64 && (player.getTileX() == 31 || player.getTileX() == 32)) d = true;
		
		boolean tele = true;
		if(l && data.zone % s == 0) tele = false;
		if(r && data.zone % s == 2) tele = false;
		if(d && data.zone + s >= s*s) tele = false;
		if(u && data.zone - s < 0) tele = false;
		if((l || u || r || d) && tele) {
			int z = data.zone;
			if(l) data.zone--;
			else if(r) data.zone++;
			else if(u) data.zone -= s;
			else data.zone += s;
			if(chooseLevel(data.zone) == null)
				data.zone = z;
			else{
				level = chooseLevel(data.zone);
				player.init(level);
				if(l) player.setPlayerTilePos(63, 32);
				else if(r) player.setPlayerTilePos(1, 32);
				else if(u) player.setPlayerTilePos(32, 63);
				else if(d) player.setPlayerTilePos(32, 1);
				level.initPlayer(player);
			}
		}
	}
	
	private Level chooseLevel(int n) {
		if(n == 4) return Level.map1zone4;
		else if(n == 5) return Level.map1zone5;
		else if(n == 1) return Level.map1zone1;
		else return null;
	}
	
	public void render(Screen screen, UIScreen uiScreen) {
		double xScroll = player.getX() - screen.width/2;
		double yScroll = player.getY() - screen.height/2;
		level.render((int)xScroll, (int)yScroll, screen);
		player.render(screen);
		level.renderOnTop(screen);
		uiScreen.render(screen);
	}
	
	public Level getLevel() {
		return level;
	}
	
	public Player getPlayer() {
		return player;
	}

}
