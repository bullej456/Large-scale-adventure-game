package com.graphics;

public class Animation {
	
	private AnimatedSprite as;
	private int nFrames, count = 0;
	private boolean end = true;
	
	public Animation(AnimatedSprite as, int frameRate, int nFrames) {
		this.as = as;
		this.nFrames = nFrames;
		as.setFrame(0);
		as.setFrameRate(frameRate);
	}
	
	public void update() {
		if(!end) {
			as.update();
			count++;
			if(count >= nFrames) end = true;
		}
	}
	
	public void reset() {
		end = false;
		count = 0;
		as.setFrame(0);
	}
	
	public boolean isEnd() {
		return end;
	}
	
	public Sprite getSprite() {
		if(end) return null;
		return as.getSprite();
	}

}
