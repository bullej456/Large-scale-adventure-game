package com.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public final int WIDTH, HEIGHT;
	private int width, height;
	public int[] pixels;
	
	public static SpriteSheet zone1 = new SpriteSheet("/textures/levels/map6.png", 66);
	public static SpriteSheet zone2 = new SpriteSheet("/textures/levels/map5.png", 66);
	
	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/sheet1.png", 96);
	public static SpriteSheet tiles2 = new SpriteSheet("/textures/sheets/sheet2.png", 96);
	public static SpriteSheet projectiles = new SpriteSheet("/textures/sheets/projectiles.png", 48);
	public static SpriteSheet objects = new SpriteSheet("/textures/sheets/objects.png", 96);
	public static SpriteSheet plants = new SpriteSheet("/textures/sheets/plants.png", 96);
	public static SpriteSheet swoop = new SpriteSheet("/textures/sheets/swoop.png", 16, 64);

	public static SpriteSheet fireX = new SpriteSheet("/textures/sheets/fire.png", 16, 80);
	public static SpriteSheet fire = new SpriteSheet(fireX, 0, 0, 1, 5, 16);
	
	public static SpriteSheet heartX = new SpriteSheet("/textures/sheets/heart.png", 16, 64);
	public static SpriteSheet heart = new SpriteSheet(heartX, 0, 0, 1, 4, 16);
	public static SpriteSheet stamX = new SpriteSheet("/textures/sheets/stamBar.png", 16, 64);
	public static SpriteSheet stam = new SpriteSheet(stamX, 0, 0, 1, 4, 16);
	public static SpriteSheet xpOrbX = new SpriteSheet("/textures/sheets/xpOrb.png", 16, 64);
	public static SpriteSheet xpOrb = new SpriteSheet(xpOrbX, 0, 0, 1, 4, 16);
	public static SpriteSheet xpJarX = new SpriteSheet("/textures/sheets/xpJar.png", 16, 80);
	public static SpriteSheet xpJar = new SpriteSheet(xpJarX, 0, 0, 1, 5, 16);
	
	public static SpriteSheet lightBall = new SpriteSheet(objects, 0, 1, 1, 2, 16);
	public static SpriteSheet stove = new SpriteSheet(objects, 5, 0, 1, 3, 16);
	public static SpriteSheet eKey = new SpriteSheet(objects, 1, 1, 1, 2, 16);
	public static SpriteSheet waterE = new SpriteSheet(objects, 4, 0, 1, 3, 16);
	public static SpriteSheet grassE = new SpriteSheet(objects, 3, 2, 1, 3, 16);
	public static SpriteSheet boulder = new SpriteSheet(tiles2, 2, 1, 1, 3, 16);
	public static SpriteSheet shooter = new SpriteSheet(plants, 2, 4, 1, 2, 16);
	public static SpriteSheet spawn = new SpriteSheet(tiles2, 1, 3, 1, 3, 16);
	public static SpriteSheet chestO = new SpriteSheet(tiles2, 5, 1, 1, 3, 16);
	
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/player.png", 64, 64);
	public static SpriteSheet player3 = new SpriteSheet("/textures/sheets/player3.png", 64, 64);
	public static SpriteSheet playerD = new SpriteSheet(player, 3, 0, 1, 4, 16);
	public static SpriteSheet playerU = new SpriteSheet(player, 0, 0, 1, 4, 16);
	public static SpriteSheet playerL = new SpriteSheet(player, 2, 0, 1, 4, 16);
	public static SpriteSheet playerR = new SpriteSheet(player, 1, 0, 1, 4, 16);
	
	public static SpriteSheet player2 = new SpriteSheet("/textures/sheets/player2.png", 64, 96);
	public static SpriteSheet player2D = new SpriteSheet(player2, 0, 1, 1, 4, 16);
			
	public static SpriteSheet rat = new SpriteSheet("/textures/sheets/rat.png", 64);
	public static SpriteSheet ratD = new SpriteSheet(rat, 3, 0, 1, 4, 16);
	public static SpriteSheet ratU = new SpriteSheet(rat, 0, 0, 1, 4, 16);
	public static SpriteSheet ratL = new SpriteSheet(rat, 2, 0, 1, 4, 16);
	public static SpriteSheet ratR = new SpriteSheet(rat, 1, 0, 1, 4, 16);
	public static SpriteSheet dummy = new SpriteSheet("/textures/sheets/dummy.png", 64);
	public static SpriteSheet dummyD = new SpriteSheet(dummy, 3, 0, 1, 4, 16);
	public static SpriteSheet dummyU = new SpriteSheet(dummy, 0, 0, 1, 4, 16);
	public static SpriteSheet dummyL = new SpriteSheet(dummy, 2, 0, 1, 4, 16);
	public static SpriteSheet dummyR = new SpriteSheet(dummy, 1, 0, 1, 4, 16);
	public static SpriteSheet bFly = new SpriteSheet("/textures/sheets/butterfly.png", 64);
	public static SpriteSheet bFlyD = new SpriteSheet(bFly, 0, 0, 1, 4, 16);
	public static SpriteSheet bFlyU = new SpriteSheet(bFly, 3, 0, 1, 4, 16);
	public static SpriteSheet bFlyL = new SpriteSheet(bFly, 2, 0, 1, 4, 16);
	public static SpriteSheet bFlyR = new SpriteSheet(bFly, 1, 0, 1, 4, 16);
	
	public static SpriteSheet stick = new SpriteSheet("/textures/sheets/stick.png", 64);
	public static SpriteSheet stickU = new SpriteSheet(stick, 0, 0, 1, 4, 16);
	public static SpriteSheet stickR = new SpriteSheet(stick, 1, 0, 1, 4, 16);
	public static SpriteSheet stickD = new SpriteSheet(stick, 2, 0, 1, 4, 16);
	public static SpriteSheet stickL = new SpriteSheet(stick, 3, 0, 1, 4, 16);
	public static SpriteSheet sword = new SpriteSheet("/textures/sheets/sword.png", 96);
	public static SpriteSheet swordU = new SpriteSheet(sword, 0, 0, 1, 4, 24);
	public static SpriteSheet swordR = new SpriteSheet(sword, 1, 0, 1, 4, 24);
	public static SpriteSheet swordD = new SpriteSheet(sword, 2, 0, 1, 4, 24);
	public static SpriteSheet swordL = new SpriteSheet(sword, 3, 0, 1, 4, 24);
	public static SpriteSheet hand = new SpriteSheet("/textures/sheets/hand.png", 64);
	public static SpriteSheet handU = new SpriteSheet(hand, 0, 0, 1, 4, 16);
	public static SpriteSheet handR = new SpriteSheet(hand, 1, 0, 1, 4, 16);
	public static SpriteSheet handD = new SpriteSheet(hand, 3, 0, 1, 4, 16);
	public static SpriteSheet handL = new SpriteSheet(hand, 2, 0, 1, 4, 16);
	
	public static SpriteSheet ghost = new SpriteSheet("/textures/sheets/ghost.png", 64);
	public static SpriteSheet ghostD = new SpriteSheet(ghost, 0, 0, 1, 4, 16);
	public static SpriteSheet ghostU = new SpriteSheet(ghost, 3, 0, 1, 4, 16);
	public static SpriteSheet ghostR = new SpriteSheet(ghost, 1, 0, 1, 4, 16);
	public static SpriteSheet ghostL = new SpriteSheet(ghost, 2, 0, 1, 4, 16);
	public static SpriteSheet bandit = new SpriteSheet("/textures/sheets/bandit.png", 64, 64);
	public static SpriteSheet banditD = new SpriteSheet(bandit, 0, 0, 1, 4, 16);
	public static SpriteSheet banditU = new SpriteSheet(bandit, 3, 0, 1, 4, 16);
	public static SpriteSheet banditR = new SpriteSheet(bandit, 1, 0, 1, 4, 16);
	public static SpriteSheet banditL = new SpriteSheet(bandit, 2, 0, 1, 4, 16);
	
	public static SpriteSheet glowX = new SpriteSheet("/textures/animations/glow.png", 16, 64);
	public static SpriteSheet glow = new SpriteSheet(glowX, 0, 0, 1, 4, 16);
	
	private Sprite[] sprites;
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if(width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w*h];
		for(int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for(int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height; ya++) {
			for(int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize*spriteSize];
				for(int y0 = 0; y0 < spriteSize; y0++) {
					for(int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[
						(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE*SIZE];
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH*HEIGHT];
		load();
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(new File("res"+path));
			width = image.getWidth();
			height = image.getHeight();
			pixels = new int[width*height];
			image.getRGB(0, 0, width, height, pixels, 0, width);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[] getPixels() {
		return pixels;
	}

}
