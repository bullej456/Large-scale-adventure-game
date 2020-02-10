package com.level.tile;

import com.Main.Screen;
import com.graphics.Sprite;

public class Tile {
	
	public int x, y;
	public Sprite sprite;
	
	public static Tile water = new WaterTile(Sprite.water);
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile burntGrass = new GrassTile(Sprite.burntGrass);
	public static Tile grass2 = new GrassTile(Sprite.grass2);
	public static Tile grassFW = new GrassTile(Sprite.grassFW);
	public static Tile grassFR = new GrassTile(Sprite.grassFR);
	public static Tile grassFY = new GrassTile(Sprite.grassFY);
	public static Tile snow = new GrassTile(Sprite.snow);
	public static Tile sand = new GrassTile(Sprite.sand);
	public static Tile grassR = new RockTile(Sprite.grassR);
	public static Tile dirt = new GrassTile(Sprite.dirt);
	public static Tile dirtU = new GrassTile(Sprite.dirtU);
	public static Tile dirtR = new GrassTile(Sprite.dirtR);
	public static Tile dirtD = new GrassTile(Sprite.dirtD);
	public static Tile dirtL = new GrassTile(Sprite.dirtL);
	public static Tile dirtTL = new GrassTile(Sprite.dirtTL);
	public static Tile dirtTR = new GrassTile(Sprite.dirtTR);
	public static Tile dirtBL = new GrassTile(Sprite.dirtBL);
	public static Tile dirtBR = new GrassTile(Sprite.dirtBR);
	public static Tile waterD = new EdgeTile(Sprite.waterD);
	public static Tile waterU = new EdgeTile(Sprite.waterU);
	public static Tile waterR = new EdgeTile(Sprite.waterR);
	public static Tile waterL = new EdgeTile(Sprite.waterL);
	public static Tile waterTR = new EdgeTile(Sprite.waterTR);
	public static Tile waterTL = new EdgeTile(Sprite.waterTL);
	public static Tile waterBR = new EdgeTile(Sprite.waterBR);
	public static Tile waterBL = new EdgeTile(Sprite.waterBL);
	public static Tile waterCBR = new EdgeTile(Sprite.waterCBR);
	public static Tile waterCBL = new EdgeTile(Sprite.waterCBL);
	public static Tile teleport = new GrassTile(Sprite.teleport);
	public static Tile treeTTile = new RockTile(Sprite.treeTTile);
	public static Tile treeT2Tile = new RockTile(Sprite.treeT2Tile);
	public static Tile treeBTile = new RockTile(Sprite.treeBTile);
	public static Tile hillU = new RockTile(Sprite.hillU);
	public static Tile hillR = new RockTile(Sprite.hillR);
	public static Tile hillD = new RockTile(Sprite.hillD);
	public static Tile hillL = new RockTile(Sprite.hillL);
	public static Tile hillBL = new RockTile(Sprite.hillBL);
	public static Tile hillBR = new RockTile(Sprite.hillBR);
	

	public static Tile woodFloor = new GrassTile(Sprite.woodFloor);
	public static Tile wall = new RockTile(Sprite.wall);
	public static Tile wallRock = new RockTile(Sprite.wallRock);
	public static Tile wallTHouse = new RockTile(Sprite.wallTHouse);
	public static Tile wallSHouse = new RockTile(Sprite.wallSHouse);
	public static Tile voidT = new VoidTile(Sprite.voidT);
	public static Tile ceramicFloor = new GrassTile(Sprite.ceramicFloor);
	public static Tile ceramicFloor2 = new GrassTile(Sprite.ceramicFloor2);
	public static Tile wallBlue = new RockTile(Sprite.wallBlue);
	public static Tile wallGreen = new RockTile(Sprite.wallGreen);
	public static Tile desk = new RockTile(Sprite.desk);
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	//Here tiles are rendered to screen by taking the tile precision
	//and multiplying by 16 to bring up to pixel precision.
	//GO TO SCREEN...
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return false;
	}
	
	public boolean projSolid() {
		return false;
	}
}
