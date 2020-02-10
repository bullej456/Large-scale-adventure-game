package com.entity.object;

import com.graphics.Sprite;

public class SolidObject extends Object{

	public SolidObject(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		solid = true;
		projSolid = true;
	}

}
