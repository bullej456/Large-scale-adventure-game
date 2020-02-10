package com.level;

public class TileCoor {
	
	private int x, y;
	
	public TileCoor(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int x() {
		return x*16;
	}
	
	public int y() {
		return y*16;
	}

}
