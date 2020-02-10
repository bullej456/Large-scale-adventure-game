package com.level.tile;

import com.graphics.Sprite;

public class EdgeTile extends Tile{

	public EdgeTile(Sprite sprite) {
		super(sprite);
		// TODO Auto-generated constructor stub
	}
	
	public boolean solid() {
		return true;
	}

}
