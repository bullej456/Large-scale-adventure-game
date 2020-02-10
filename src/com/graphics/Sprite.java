package com.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	protected SpriteSheet sheet;
	private int width, height;

	public static Sprite water = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite burntGrass = new Sprite(16, 0, 5, SpriteSheet.tiles);
	public static Sprite grass2 = new Sprite(16, 0, 4, SpriteSheet.tiles);
	public static Sprite snow = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite sand = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite grassFW = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite grassFR = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite grassFY = new Sprite(16, 2, 1, SpriteSheet.tiles);
	public static Sprite grassR = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite wall = new Sprite(16, 4, 1, SpriteSheet.tiles);
	public static Sprite wallRock = new Sprite(16, 4, 2, SpriteSheet.tiles);
	public static Sprite woodFloor = new Sprite(16, 5, 1, SpriteSheet.tiles);
	public static Sprite voidT = new Sprite(16, 2, 0, SpriteSheet.plants);
	public static Sprite ceramicFloor = new Sprite(16, 5, 2, SpriteSheet.tiles);
	public static Sprite ceramicFloor2 = new Sprite(16, 4, 4, SpriteSheet.tiles);
	public static Sprite wallBlue = new Sprite(16, 4, 3, SpriteSheet.tiles);
	public static Sprite wallGreen = new Sprite(16, 5, 3, SpriteSheet.tiles);
	public static Sprite nothing = new Sprite(16, 2, 0, SpriteSheet.plants);
	public static Sprite treeTTile = new Sprite(16, 5, 4, SpriteSheet.tiles);
	public static Sprite treeT2Tile = new Sprite(16, 3, 4, SpriteSheet.tiles);
	public static Sprite treeBTile = new Sprite(16, 5, 5, SpriteSheet.tiles);
	public static Sprite teleport = new Sprite(16, 2, 4, SpriteSheet.tiles);
	public static Sprite dirt = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite dirtU = new Sprite(16, 1, 2, SpriteSheet.tiles);
	public static Sprite dirtR = new Sprite(16, 2, 2, SpriteSheet.tiles);
	public static Sprite dirtD = new Sprite(16, 3, 2, SpriteSheet.tiles);
	public static Sprite dirtL = new Sprite(16, 3, 1, SpriteSheet.tiles);
	public static Sprite dirtTL = new Sprite(16, 0, 3, SpriteSheet.tiles);
	public static Sprite dirtTR = new Sprite(16, 1, 3, SpriteSheet.tiles);
	public static Sprite dirtBL = new Sprite(16, 3, 3, SpriteSheet.tiles);
	public static Sprite dirtBR = new Sprite(16, 2, 3, SpriteSheet.tiles);
	public static Sprite waterU = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite waterD = new Sprite(16, 1, 1, SpriteSheet.tiles2);
	public static Sprite waterR = new Sprite(16, 0, 1, SpriteSheet.tiles2);
	public static Sprite waterL = new Sprite(16, 1, 0, SpriteSheet.tiles2);
	public static Sprite waterTR = new Sprite(16, 0, 0, SpriteSheet.tiles2);
	public static Sprite waterTL = new Sprite(16, 2, 0, SpriteSheet.tiles2);
	public static Sprite waterBL = new Sprite(16, 1, 2, SpriteSheet.tiles2);
	public static Sprite waterBR = new Sprite(16, 0, 2, SpriteSheet.tiles2);
	public static Sprite waterCBL = new Sprite(16, 4, 4, SpriteSheet.tiles2);
	public static Sprite waterCBR = new Sprite(16, 3, 4, SpriteSheet.tiles2);
	public static Sprite hillU = new Sprite(16, 3, 3, SpriteSheet.tiles2);
	public static Sprite hillD = new Sprite(16, 3, 1, SpriteSheet.tiles2);
	public static Sprite hillR = new Sprite(16, 3, 2, SpriteSheet.tiles2);
	public static Sprite hillL = new Sprite(16, 3, 0, SpriteSheet.tiles2);
	public static Sprite hillBL = new Sprite(16, 4, 1, SpriteSheet.tiles2);
	public static Sprite hillBR = new Sprite(16, 4, 2, SpriteSheet.tiles2);
	public static Sprite wallTHouse = new Sprite(16, 3, 5, SpriteSheet.tiles2);
	public static Sprite wallSHouse = new Sprite(16, 4, 5, SpriteSheet.tiles2);
	
	public static Sprite shooterOff = new Sprite(16, 2, 3, SpriteSheet.plants);
	
	public static Sprite redBall = new Sprite(16, 0, 0, SpriteSheet.projectiles);
	public static Sprite desk = new Sprite(16, 0, 0, SpriteSheet.objects);
	public static Sprite redStick = new Sprite(8, 2, 0xff0000);
	public static Sprite trunk = new Sprite(16, 1, 0, SpriteSheet.objects);
	public static Sprite tree = new Sprite(48, 0, 1, SpriteSheet.objects);
	public static Sprite stool = new Sprite(16, 2, 0, SpriteSheet.objects);
	public static Sprite lightBall = new Sprite(16, 0, 1, SpriteSheet.objects);
	public static Sprite stove = new Sprite(16, 2, 1, SpriteSheet.objects);
	public static Sprite stoveFan = new Sprite(16, 3, 1, SpriteSheet.objects);
	public static Sprite fence = new Sprite(16, 2, 2, SpriteSheet.objects);
	public static Sprite signBottom = new Sprite(16, 4, 4, SpriteSheet.objects);
	public static Sprite signTop = new Sprite(16, 4, 3, SpriteSheet.objects);
	public static Sprite signWall = new Sprite(16, 3, 4, SpriteSheet.objects);
	public static Sprite bookshelfTop = new Sprite(16, 5, 3, SpriteSheet.objects);
	public static Sprite bookshelfBot = new Sprite(16, 5, 4, SpriteSheet.objects);
	public static Sprite tableFront = new Sprite(16, 3, 5, SpriteSheet.objects);
	public static Sprite tableBack = new Sprite(16, 4, 5, SpriteSheet.objects);
	public static Sprite desk2 = new Sprite(16, 5, 5, SpriteSheet.objects);
	public static Sprite cable = new Sprite(16, 3, 0, SpriteSheet.objects);
	public static Sprite boulder = new Sprite(16, 0, 0, SpriteSheet.boulder);
	public static Sprite spawner = new Sprite(16, 0, 3, SpriteSheet.tiles2);
	public static Sprite plaque = new Sprite(16, 0, 4, SpriteSheet.tiles2);
	public static Sprite runRight = new Sprite(16, 0, 5, SpriteSheet.tiles2);
	public static Sprite chestC = new Sprite(16, 5, 0, SpriteSheet.tiles2);
	public static Sprite rock = new Sprite(16, 0, 0, SpriteSheet.plants);

	public static Sprite plant1 = new Sprite(16, 0, 0, SpriteSheet.plants);
	public static Sprite plant1B = new Sprite(16, 0, 1, SpriteSheet.plants);
	public static Sprite plant2 = new Sprite(16, 1, 0, SpriteSheet.plants);
	public static Sprite plant2B = new Sprite(16, 1, 1, SpriteSheet.plants);
	public static Sprite grassA1 = new Sprite(16, 4, 0, SpriteSheet.plants);
	public static Sprite grassA2 = new Sprite(16, 5, 0, SpriteSheet.plants);
	public static Sprite doorClosed = new Sprite(16, 4, 1, SpriteSheet.plants);
	public static Sprite doorOpen = new Sprite(16, 5, 1, SpriteSheet.plants);
	public static Sprite bush = new Sprite(16, 0, 2, SpriteSheet.plants);
	public static Sprite bush2 = new Sprite(16, 0, 3, SpriteSheet.plants);
	public static Sprite tree1a = new Sprite(16, 1, 2, SpriteSheet.plants);
	public static Sprite tree1b = new Sprite(16, 1, 3, SpriteSheet.plants);
	public static Sprite waterE = new Sprite(16, 4, 0, SpriteSheet.objects);
	public static Sprite tree2aa = new Sprite(16, 3, 3, SpriteSheet.plants);
	public static Sprite tree2ab = new Sprite(16, 4, 3, SpriteSheet.plants);
	public static Sprite tree2ba = new Sprite(16, 3, 2, SpriteSheet.plants);
	public static Sprite tree2bb = new Sprite(16, 4, 2, SpriteSheet.plants);
	public static Sprite statueB = new Sprite(16, 2, 5, SpriteSheet.tiles2);
	public static Sprite statueT = new Sprite(16, 2, 4, SpriteSheet.tiles2);
	public static Sprite niceBush = new Sprite(16, 5, 5, SpriteSheet.tiles2);

	public static Sprite garlicUnPicked = new Sprite(16, 3, 3, SpriteSheet.objects);
	public static Sprite garlicPicked = new Sprite(16, 3, 2, SpriteSheet.objects);
	public static Sprite strawP = new Sprite(16, 3, 0, SpriteSheet.plants);
	public static Sprite strawUnp = new Sprite(16, 3, 1, SpriteSheet.plants);
	public static Sprite pFlowerUnp = new Sprite(16, 0, 4, SpriteSheet.plants);
	public static Sprite pFlowerP = new Sprite(16, 0, 5, SpriteSheet.plants);
	public static Sprite rFlowerUnp = new Sprite(16, 1, 4, SpriteSheet.plants);
	public static Sprite rFlowerP = new Sprite(16, 1, 5, SpriteSheet.plants);
	
	public static Sprite playerD = new Sprite(16, 3, 0, SpriteSheet.player);
	public static Sprite playerU = new Sprite(16, 0, 0, SpriteSheet.player);
	public static Sprite playerL = new Sprite(16, 2, 0, SpriteSheet.player);
	public static Sprite playerR = new Sprite(16, 1, 0, SpriteSheet.player);
	
	public static Sprite particle = new Sprite(1, 0x772F00);
	public static Sprite particleBlack = new Sprite(2, 0xff000000);
	public static Sprite particleBlue = new Sprite(2, 0xff4dd2ff);
	public static Sprite particleRed = new Sprite(1, 0xffff0000);
	public static Sprite particlePink = new Sprite(2, 0xffff9696);
	public static Sprite particleYellow = new Sprite(1, 0xffffffff);
	public static Sprite blood = new Sprite(2, 0xFF0000);

	public static Sprite swoopU = new Sprite(16, 0, 0, SpriteSheet.swoop);
	public static Sprite swoopR = new Sprite(16, 0, 1, SpriteSheet.swoop);
	public static Sprite swoopD = new Sprite(16, 0, 2, SpriteSheet.swoop);
	public static Sprite swoopL = new Sprite(16, 0, 3, SpriteSheet.swoop);

	public static Sprite heart = new Sprite(16, 0, 0, SpriteSheet.heart);
	public static Sprite stam = new Sprite(16, 0, 0, SpriteSheet.stam);
	public static Sprite zone1 = new Sprite(66, 0, 0, SpriteSheet.zone1);
	public static Sprite zone2 = new Sprite(66, 0, 0, SpriteSheet.zone2);

	public static Sprite statueBig = new Sprite(450, 0, 0, new SpriteSheet("/textures/sheets/statueBig.png", 450));
	
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = SIZE;
		this.height = SIZE;
		pixels = new int[SIZE*SIZE];
		this.x = x*size;
		this.y = y*size;
		this.sheet = sheet;
		load();
	}
	
	public Sprite(int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width*height];
		setColour(colour);
	}
	
	public Sprite(int size, int colour) {
		SIZE = size;
		this.width = SIZE;
		this.height = SIZE;
		pixels = new int[SIZE*SIZE];
		setColour(colour);
	}
	
	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getWidth()*sheet.getHeight()) / (sheet.WIDTH*sheet.HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.WIDTH*sheet.HEIGHT];
		
		for(int yp = 0; yp < sheet.getHeight()/sheet.HEIGHT; yp++) {
			for(int xp = 0; xp < sheet.getWidth()/sheet.WIDTH; xp++) {
				
				for(int y = 0; y < sheet.HEIGHT; y++) {
					for(int x = 0; x < sheet.WIDTH; x++) {
						int xo = (x+xp*sheet.WIDTH);
						int yo = (y+yp*sheet.HEIGHT);
						pixels[x+y*sheet.WIDTH] = sheet.getPixels()[xo + yo *sheet.getWidth()];
					}
				}
				
				sprites[current++] = new Sprite(pixels.clone(), sheet.WIDTH, sheet.HEIGHT);
			}
		}
		return sprites;
	}
	
	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	private void setColour(int colour) {
		for(int i = 0; i < width*height; i++) {
			pixels[i] = colour;
		}
	}
	
	private void load() {
		for(int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) {
				pixels[x+y*width] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.WIDTH];
			}
		}
	}
}
