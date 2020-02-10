package com.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.entity.mob.Dummy;
import com.entity.mob.Goblin;

public class SpawnLevel extends Level{

	public SpawnLevel(String path, String path2, int zone) {
		super(path, path2, zone);
	}
	
	protected void loadLevel(String path, String path2, int zone) {
		try {
			BufferedImage image = ImageIO.read(new File("res"+path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width*height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load level!");
		}
		try {
			BufferedImage image = ImageIO.read(new File("res"+path2));
			e = new int[width*height];
			image.getRGB(0, 0, width, height, e, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Could not load entity map!");
		}
		loadEntityMap();
		
		if(zone == 5) {
			for(int i = 0; i < 9; i++) add(new Goblin(10+random.nextInt(4), 50+random.nextInt(4), 20));
			
		}
		else if(zone == 1) {
			for(int i = 0; i < 3; i++) add(new Dummy(32+random.nextInt(4), 50+random.nextInt(4), 20));
		}
		else if(zone == 4) {
			for(int i = 0; i < 3; i++) add(new Dummy(30+random.nextInt(5), 14+random.nextInt(4), 20));
		}
	}
	

}
