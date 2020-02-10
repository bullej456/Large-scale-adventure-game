package com.graphics;

public class AnimatedSprite extends Sprite{
	
	private int frame = 0;
	private Sprite sprite;
	private int rate = 10;
	private int time = 0;
	private int length = -1;
	
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		this.length = length;
		sprite = sheet.getSprites()[0];
	}
	
	public void update() {
		time++;
		if(time % rate == 0) {
			if(frame >= length - 1) frame = 0;
			else frame++;
			sprite = sheet.getSprites()[frame];
		}
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void setFrameRate(int rate) {
		this.rate = rate;
	}
	
	public void setFrame(int index) {
		if(index > sheet.getSprites().length -1) return;
		sprite = sheet.getSprites()[index];
	}

}
