package com.Main;

import java.util.Random;

import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.level.tile.Tile;

public class Screen {
	
	public int width, height;
	public int[] pixels;
	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE -1;
	
	public int xOffset, yOffset;
	
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];
	
	private Random random = new Random();
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		
		for(int i = 0; i < MAP_SIZE*MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if(fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for(int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for(int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				if(xa < -sheet.WIDTH || xa >= width 
						|| ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = sheet.pixels[x + y*sheet.WIDTH];
				if(col != 0xffff00ff) pixels[xa + ya *width] = col;
			}
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if(fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for(int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if(xa < -sprite.getWidth() || xa >= width 
						|| ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[x + y*sprite.getWidth()];
				if(col != 0xffff00ff) pixels[xa + ya *width] = col;
			}
		}
	}
	
	public void renderTextChar(int xp, int yp, Sprite sprite, int colour, boolean fixed) {
		if(fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for(int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if(xa < -sprite.getWidth() || xa >= width 
						|| ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[x + y*sprite.getWidth()];
				if(col != 0xffff00ff) pixels[xa + ya *width] = colour;
			}
		}
	}
	
	//This is called from each tile within the level with pixel precision inputs
	//xp and yp, which represent the position of the tile within the whole level
	//in pixels. The offset is subtracted, which was set by key inputs,
	//to find its position relative to the screen. Then this loops through
	//the tile, pixel by pixel by making xa and ya, the position of each pixel
	//within the tile, in the pixel array.
	//They are only rendered if they're within a tile on the screen.
	//GO TO MAIN...
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if(xa < -tile.sprite.SIZE || xa >= width 
						|| ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				pixels[xa + ya *width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}
	
	public void renderMob(int xp, int yp, Sprite sprite) {
		xp -= xOffset;
		yp -= yOffset;
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if(xa < -sprite.SIZE || xa >= width 
						|| ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				int col = sprite.pixels[x + y*sprite.SIZE];
				if(col != 0xffff00ff) pixels[xa + ya *width] = col;
			}
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

}
