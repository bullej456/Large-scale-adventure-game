package com.entity.object;

import com.graphics.Sprite;

public class TopObject extends Object{

	public TopObject(int x, int y, Sprite sprite) {
		super(x, y, sprite);
		onTop = true;
	}

}
