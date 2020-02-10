package com.level.tile;

import com.graphics.Sprite;

public class RockTile extends Tile{

	public RockTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public boolean solid() {
		return true;
	}
	
	public boolean projSolid() {
		return true;
	}

}
