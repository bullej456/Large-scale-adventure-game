package com.Main;

public class XPManager {
	
	private int xp, nextXp, level;
	
	public XPManager() {
		xp = (int) Main.data.xp;
		nextXp = (int) Main.data.nextXp;
		level = (int) Main.data.level;
	}
	
	public void update() {
		if(xp > nextXp) {
			level++;
			Main.musicPlayer.playSound(5, -5f);
			nextXp = (int) (nextXp*1.2 + 50*(level-1));
			xp = 0;
			Main.data.nextXp = nextXp;
			Main.data.level = level;
		}
		Main.data.xp = xp;
	}
	
	public void addXP(int xp) {
		this.xp += xp;
		Main.musicPlayer.playSound(13, -10f);
	}

}
