package com.level;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.Main.InventorySlot.Ingredient;
import com.Main.Main;
import com.Main.NPCManager;
import com.Main.Screen;
import com.Main.Vector2i;
import com.entity.Entity;
import com.entity.Particle;
import com.entity.mob.Chaser;
import com.entity.mob.Dummy;
import com.entity.mob.Mob;
import com.entity.mob.Mob.Dir;
import com.entity.mob.NPC;
import com.entity.mob.Player;
import com.entity.object.AnimObject;
import com.entity.object.DoorObject;
import com.entity.object.Object;
import com.entity.object.PickableObject;
import com.entity.object.PlayerAnimObject;
import com.entity.object.ReadableObject;
import com.entity.object.SolidObject;
import com.entity.object.TopObject;
import com.entity.object.active.ChestObject;
import com.entity.object.active.ProjectileBoxObject;
import com.entity.object.active.PushableObject;
import com.entity.projectile.Projectile;
import com.entity.spawner.ChaserSpawner;
import com.entity.spawner.DummySpawner;
import com.graphics.AnimatedSprite;
import com.graphics.FloatFont;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.item.HeartItem;
import com.item.Item;
import com.item.XPOrbItem;
import com.level.tile.Tile;

public class Level {
	
	protected int width, height;
	protected int[] tiles, e;
	protected static final Random random = new Random();
	
	protected boolean eKey = false;
	protected AnimatedSprite eKeyAnim = new AnimatedSprite(SpriteSheet.eKey, 16, 16, 2);
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	private List<Particle> particles = new ArrayList<Particle>();
	private List<FloatFont> floatFonts = new ArrayList<FloatFont>();
	//protected List<Spawner> spawners = new ArrayList<Spawner>();
	
	protected Rain rain;
	protected boolean rainOn = true;
	
	private Player player;
	
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if(n1.f < n0.f) return 1;
			else if(n1.f > n0.f) return -1;
			return 0;
		}
	};
	
	public static Level map1zone5 = new SpawnLevel("/textures/levels/level3.png",
			"/textures/levels/ents3.png", 5);
	public static Level map1zone4 = new SpawnLevel("/textures/levels/zone2.png",
					"/textures/levels/zone2ents.png", 4);
	public static Level map1zone1 = new SpawnLevel("/textures/levels/z1.png",
			"/textures/levels/z1ents.png", 1);
	
	public Level(String path, String path2, int zone) {
		loadLevel(path, path2, zone);
		generateLevel();
		eKeyAnim.setFrameRate(20);
		rain = new Rain(600);
		new NPCManager(this, zone);
	}
	
	public void initPlayer(Player player) {
		this.player = player;
	}
	
	//This method is called within the child class.
	//GO TO CHILD... eg. RandomLevel.
	protected void generateLevel() {
		
	}
	
	protected void loadLevel(String path) {
		
	}
	
	protected void loadLevel(String path, String path2, int zone) {
		
	}
	
	public void update() {
//		for(Spawner s: spawners) {
//			s.update();
//		}
		
//		rain.update(player.getTileX(), player.getTileY());
//		if(rainOn && random.nextInt(1000) == 0) {
//			rain.rainOn(false);
//			rainOn = false;
//		}
//		else if(!rainOn && random.nextInt(1000) == 0) {
//			rain.rainOn(true);
//			rainOn = true;
//		}
		
		eKey = false;
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if((e instanceof Object || e instanceof NPC) && e.pressable()) eKey = true;
			else if(e instanceof HeartItem) {
				if(calcDist(e.getX()+8, e.getY()+8, player.getX(), player.getY()) < 10) {
					((HeartItem) entities.get(i)).pickup(player);
					entities.get(i).remove();
					Main.musicPlayer.playSound(4, -10f);
				}
			}
			else if(e instanceof XPOrbItem) {
				if(calcDist(e.getX()+8, e.getY()+8, player.getX(), player.getY()) < 10) {
					((XPOrbItem) entities.get(i)).pickup(player);
					entities.get(i).remove();
				}
			}
			else if(e instanceof Item && !(player.isHolding())) {
				if(((Item) e).isPickable()) {
					if(calcDist(e.getX()+8, e.getY()+8, player.getX(), player.getY()) < 10) {
						player.pickUp((Item) entities.get(i));
						entities.remove(i);
						i--;
					}
				}
			}
			entities.get(i).update();
		}
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).update();
		}
		for(int i = 0; i < floatFonts.size(); i++) {
			floatFonts.get(i).update();
		}
		remove();
		if(eKey) eKeyAnim.update();
	}
	
	private void remove() {
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).isRemoved()) entities.remove(i);
		}
		for(int i = 0; i < projectiles.size(); i++) {
			if(projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for(int i = 0; i < particles.size(); i++) {
			if(particles.get(i).isRemoved()) particles.remove(i);
		}
		for(int i = 0; i < floatFonts.size(); i++) {
			if(floatFonts.get(i).isRemoved()) floatFonts.remove(i);
		}
	}
	
	public List<Projectile> getProjectiles(){
		return projectiles;
	}
	public List<Entity> getEntities(){
		return entities;
	}
	public List<Particle> getParticles(){
		return particles;
	}
	
	public ArrayList<Node> findPath(Vector2i start, Vector2i goal){
		ArrayList<Node> openList = new ArrayList<Node>();
		ArrayList<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, calcDist(start.x, start.y, goal.x, goal.y));
		openList.add(current);
		while(openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if(current.tile.equals(goal)) {
				ArrayList<Node> path = new ArrayList<Node>();
				while(current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for(int i = 0; i < 9; i++) {
				if(i == 4) continue;
				//if(i == 0 || i == 2 || i == 6 || i == 8) continue;//Ignores diagonal.
				int x = current.tile.x;
				int y = current.tile.y;
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile at = getTile(x + xi, y + yi);
				if(at == null) continue;
				if(at.solid()) continue;
				
				boolean cont = false;
				for(Entity e: entities) {//Checks entities as well.
					if(e.getTileX() == x + xi && e.getTileY() == y + yi
					&& e.solid()) cont = true;
				}
				if(cont) continue;
				
				Vector2i a = new Vector2i(x + xi, y + yi);
				double n = calcDist(current.tile.x, current.tile.y, a.x, a.y);
				if(n >= 1) n = 1;
				else n = 0.95;
				double g = current.g + n;
				double h = calcDist(a.x, a.y, goal.x, goal.y);
				Node node = new Node(a, current, g, h);
				if(vecInList(closedList, a) && g >= node.g) continue;
				if(!vecInList(openList, a) || g < node.g) openList.add(node);
			}
			if(closedList.size() > 300) break;//////
		}
		closedList.clear();
		return null;
	}
	
	private boolean vecInList(ArrayList<Node> list, Vector2i vector) {
		for(Node n: list) {
			if(n.tile.equals(vector)) return true;
		}
		return false;
	}
	
	public boolean tileCollision(double x, double y, double xa, double ya, int size) {
		boolean solid = false;
		//Checks all corners of tiles in front, not just origin of tile in front.
		for(int c = 0; c < 4; c++) {
			int xt = (((int)x + (int)xa) + c%2*(size)/4 + 0)/16;
			int yt = (((int)y + (int)ya) + c/2*(size)/4 + 0)/16;
			
			if(getTile(xt, yt).solid()) solid = true;
			
			for(Entity e: entities) {
				if(e.getTileX() == xt && e.getTileY() == yt) {
					if(e.solid()) {
						solid = true;
						break;
					}
				}
			}
		}
		return solid;
	}
	
	public int projCollision(double x, double y, double xa, double ya, int size, int damage, boolean blast, Entity ent) {
		int solid = 0;
		//Checks all corners of tiles in front, not just origin of tile in front.
		for(int c = 0; c < 4; c++) {
			int xt = (((int)x + (int)xa) + c%2*size/4 + 0)/16;
			int yt = (((int)y + (int)ya) + c/2*size/4 + 0)/16;
			if(getTile(xt, yt).projSolid()) solid = 1;

			//Move projectile hit left on mobs!!!!!!!!!!!
			if(!(ent instanceof Player)) {
				Player p = player;
				if(p.getTileX() == xt && p.getTileY() == yt) {
					if(p.projSolid()) {
						solid = 1;
						break;
					}
					p.setHit(damage);
					if(blast) {
						for(Entity ee: entities) {
							if(ee instanceof Mob) {
								if(calcDist(x, y, ee.getX(), ee.getY()) < 60) {
									((Mob)ee).setHit((int)(damage/2f));
								}
							}
						}
					}
					return 2;
				}
			}
			///////
			
			for(Entity e: entities) {
				if(e.equals(ent)) continue;
				if(e.getTileX() == xt && e.getTileY() == yt) {
					if(e.projSolid()) {
						solid = 1;
						break;
					}
					if(e instanceof Mob && !(e instanceof Dummy)) {
						((Mob)e).setHit(damage);
						if(blast) {
							for(Entity ee: entities) {
								if(ee instanceof Mob) {
									if(!(ee.equals(e)) && calcDist(x, y, ee.getX(), ee.getY()) < 60) {
										((Mob)ee).setHit((int)(damage/2f));
									}
								}
							}
						}
						return 2;
					}
				}
			}
		}
		return solid;
	}
	
	public double calcDist(double x, double y, double xx, double yy) {
		double distance = 1000;
		double dx = Math.abs(Math.abs(x)-Math.abs(xx));
		double dy = Math.abs(Math.abs(y)-Math.abs(yy));
		distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}
	
	//An x and y is passed which is dependent on key presses.
	//These are passed as offsets to screen.
	//Corner pins are created in Tile precision by dividing the
	//x and y by 16.
	//Then it loops through tiles between the pins, getting the
	//tile each time and calling its render method.
	//GO TO TILE...
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}
		
		//rain.renderB(screen);
		
		for(int i = 0; i < particles.size(); i++) {
			particles.get(i).render(screen);
		}
		for(int i = 0; i < entities.size(); i++) {
			if(!entities.get(i).onTop()) entities.get(i).render(screen);
		}
//		for(Spawner s: spawners) s.render(screen);
		for(int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
	}
	
	public void renderOnTop(Screen screen) {
		for(int i = 0; i < entities.size(); i++) {
			if(entities.get(i).onTop()) entities.get(i).render(screen);
		}
		for(int i = 0; i < floatFonts.size(); i++) {
			floatFonts.get(i).render(screen);
		}
		//rain.renderT(screen);
		if(eKey) {
			screen.renderSprite((int) (width*0.05), (int) (height*0.05),
					eKeyAnim.getSprite(), false);
		}
	}
	
	public void add(Entity e) {
		if(e != null) {
			e.init(this);
			if(e instanceof Particle) particles.add((Particle)e);
			else if(e instanceof Projectile) projectiles.add((Projectile) e);
			else if(e instanceof FloatFont) floatFonts.add((FloatFont) e);
			else entities.add(e);
		}
	}
	
	public void loadEntityMap() {
		int index = 0;
		List<Object> wait = new ArrayList<Object>();
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				if(e[x+y*width] == 0xffFF6A00)
					add(new SolidObject(x, y, Sprite.desk));
				if(e[x+y*width] == 0xff0FD221)
					add(new SolidObject(x, y, Sprite.niceBush));
				if(e[x+y*width] == 0xff515151)
					add(new SolidObject(x, y, Sprite.rock));
				if(e[x+y*width] == 0xff7F0000) {
					add(new SolidObject(x, y, Sprite.trunk));
					add(new TopObject(x - 1, y - 3, Sprite.tree));
				}
				if(e[x+y*width] == 0xff007F46)
					add(new PlayerAnimObject(x, y, Sprite.bush, Sprite.bush2));
				if(e[x+y*width] == 0xff0000ff)
					add(new AnimObject(x, y, Sprite.waterE, SpriteSheet.waterE, 60 + random.nextInt(100), true, false));
				if(e[x+y*width] == 0xffffffff)
					add(new SolidObject(x, y, Sprite.fence));
				if(e[x+y*width] == 0xffBC9D00) {
					add(new ReadableObject(x, y, Sprite.signBottom, "null"));
					index++;
					add(new TopObject(x, y - 1, Sprite.signTop));
				}
				if(e[x+y*width] == 0xffFFB27F)
					add(new PickableObject(x, y, Sprite.garlicUnPicked, Sprite.garlicPicked, 1000, "garlic", Ingredient.GARLIC));
				if(e[x+y*width] == 0xff441616) {
					add(new ReadableObject(x, y, Sprite.signWall, "null"));
					index++;
				}
				if(e[x+y*width] == 0xffff0000) {
					add(new SolidObject(x, y, Sprite.bookshelfBot));
					add(new TopObject(x, y - 1, Sprite.bookshelfTop));
				}
				if(e[x+y*width] == 0xff513420)
					add(new SolidObject(x, y, Sprite.tableFront));
				if(e[x+y*width] == 0xff512000)
					add(new SolidObject(x, y, Sprite.tableBack));
				if(e[x+y*width] == 0xff123D00)
					add(new SolidObject(x, y, Sprite.desk2));
				if(e[x+y*width] == 0xff007F0E) {
					add(new SolidObject(x, y, Sprite.plant1B));
					add(new TopObject(x, y - 1, Sprite.plant1));
				}
				if(e[x+y*width] == 0xff005608) {
					add(new SolidObject(x, y, Sprite.plant2B));
					add(new TopObject(x, y - 1, Sprite.plant2));
				}
				if(e[x+y*width] == 0xff00750B) {
					add(new SolidObject(x, y, Sprite.tree1b));
					add(new TopObject(x, y - 1, Sprite.tree1a));
				}
				if(e[x+y*width] == 0xff000000) 
					add(new ProjectileBoxObject(x, y, Sprite.shooterOff, SpriteSheet.shooter, 10, Dir.DOWN));
				if(e[x+y*width] == 0xffFF4E00)
					add(new PickableObject(x, y, Sprite.strawUnp, Sprite.strawP, 1000, "strawberry", Ingredient.STRAWBERRY));
				if(e[x+y*width] == 0xff48F200)
					add(new PlayerAnimObject(x, y, Sprite.grassA1, Sprite.grassA2));
				if(e[x+y*width] == 0xff7F3F3F)
					add(new DoorObject(x, y, Sprite.doorClosed, Sprite.doorOpen));
				if(e[x+y*width] == 0xff144400) {
					add(new SolidObject(x, y, Sprite.tree2aa));
					add(new TopObject(x, y - 1, Sprite.tree2ba));
					add(new SolidObject(x + 1, y, Sprite.tree2ab));
					add(new TopObject(x + 1, y - 1, Sprite.tree2bb));
				}
				if(e[x+y*width] == 0xff7D00B7)
					add(new PickableObject(x, y, Sprite.pFlowerUnp, Sprite.pFlowerP, 1000, "purple", Ingredient.PFLOWER));
				if(e[x+y*width] == 0xffFF0025)
					add(new PickableObject(x, y, Sprite.rFlowerUnp, Sprite.rFlowerP, 1000, "red", Ingredient.RFLOWER));
				if(e[x+y*width] == 0xff7F0037)
					wait.add(new PushableObject(x, y));
				if(e[x+y*width] == 0xff75FF85)
					add(new ChaserSpawner(x, y, 5, this, Chaser.Type.GHOST));
				if(e[x+y*width] == 0xff7D9148)
					add(new DummySpawner(x, y, 5, this));
				if(e[x+y*width] == 0xff282828) {
					add(new SolidObject(x, y, Sprite.statueB));
					add(new TopObject(x, y - 1, Sprite.statueT));
				}
				if(e[x+y*width] == 0xffFF5900)
					add(new AnimObject(x, y, Sprite.waterE, SpriteSheet.fire, 7 + random.nextInt(5), true, false));
				if(e[x+y*width] == 0xff7F006E)
					add(new ChestObject(x, y, random.nextInt(3)));
			}
		}
		
		for(Object o: wait) add(o);
	}
	
	//Uses tiles array, which holds ID's to return relevant tiles.
	public Tile getTile(int x, int y) {
		if(x <= 0 || y <= 0 || x >= width || y >= height) {
			return walls(x, y);
		}
		else {
			if(tiles[x+y*width] == 0xff00ff00) {
				if(random.nextInt(30) == 0) {
					tiles[x+y*width] = 0xffffffff;
					return Tile.grassFW;
				}
				else if(random.nextInt(10) == 0) {
					tiles[x+y*width] = 0xfffffffa;
					return Tile.grass2;
				}
				else{
					tiles[x+y*width] = 0xff00ff0a;
					return Tile.grass;
				}
			}
			if(tiles[x+y*width] == 0xff0000ff) {
				boolean u, r, d, l;
				u = r = d = l = false;
				if(tiles[(x+1)+(y)*width] == 0xffffffff || tiles[(x+1)+(y)*width] == 0xff00ff0a || tiles[(x+1)+(y)*width] == 0xfffffffa) r = true;
				if(tiles[(x-1)+(y)*width] == 0xffffffff || tiles[(x-1)+(y)*width] == 0xff00ff0a || tiles[(x-1)+(y)*width] == 0xfffffffa) l = true;
				if(tiles[(x)+(y+1)*width] == 0xffffffff || tiles[(x)+(y+1)*width] == 0xff00ff0a || tiles[(x)+(y+1)*width] == 0xfffffffa) d = true;
				if(tiles[(x)+(y-1)*width] == 0xffffffff || tiles[(x)+(y-1)*width] == 0xff00ff0a || tiles[(x)+(y-1)*width] == 0xfffffffa) u = true;
				boolean tr, tl;
				tr = tl = false;
				if(tiles[(x+1)+(y-1)*width] == 0xffffffff || tiles[(x+1)+(y-1)*width] == 0xff00ff0a || tiles[(x+1)+(y-1)*width] == 0xfffffffa) tr = true;
				if(tiles[(x-1)+(y-1)*width] == 0xffffffff || tiles[(x-1)+(y-1)*width] == 0xff00ff0a || tiles[(x-1)+(y-1)*width] == 0xfffffffa) tl = true;
				
				if(l && u) return Tile.waterTL;
				else if(r && u) return Tile.waterTR;
				else if(l && d) return Tile.waterBL;
				else if(r && d) return Tile.waterBR;
				else if(u) return Tile.waterU;
				else if(r) return Tile.waterR;
				else if(d) return Tile.waterD;
				else if(l) return Tile.waterL;
				else if(tr) return Tile.waterCBL;
				else if(tl) return Tile.waterCBR;
				else return Tile.water;
			}
			else if(tiles[x+y*width] == 0xff7F743F) return Tile.hillD;
			else if(tiles[x+y*width] == 0xff6D7F3F) return Tile.hillU;
			else if(tiles[x+y*width] == 0xff527F3F) return Tile.hillR;
			else if(tiles[x+y*width] == 0xff3F7F62) return Tile.hillL;
			else if(tiles[x+y*width] == 0xff3F7F47) return Tile.hillBR;
			else if(tiles[x+y*width] == 0xff3F7F7F) return Tile.hillBL;
			else if(tiles[x+y*width] == 0xffffffff) return Tile.grassFW;
			else if(tiles[x+y*width] == 0xfffffffa) return Tile.grass2;
			else if(tiles[x+y*width] == 0xff00ff0a) return Tile.grass;
			else if(tiles[x+y*width] == 0xff00ffff) return Tile.snow;
			else if(tiles[x+y*width] == 0xffffff00) return Tile.sand;
			else if(tiles[x+y*width] == 0xff303030) return Tile.wall;
			else if(tiles[x+y*width] == 0xff606060) return Tile.wallRock;
			else if(tiles[x+y*width] == 0xff7f3300) return Tile.woodFloor;
			else if(tiles[x+y*width] == 0xffE5E5E5) return Tile.ceramicFloor;
			else if(tiles[x+y*width] == 0xffF7E7E1) return Tile.ceramicFloor2;
			else if(tiles[x+y*width] == 0xffD9EFEF) return Tile.wallBlue;
			else if(tiles[x+y*width] == 0xffC2FFBE) return Tile.wallGreen;
			else if(tiles[x+y*width] == 0xffFF6A00) return Tile.desk;
			else if(tiles[x+y*width] == 0xff259930) return Tile.burntGrass;
			else if(tiles[x+y*width] == 0xff404040) return Tile.wallTHouse;
			else if(tiles[x+y*width] == 0xff707070) return Tile.wallSHouse;
			else if(tiles[x+y*width] == 0xffC4A300) {
				boolean u, r, d, l;
				u = r = d = l = false;
				if(tiles[(x+1)+(y)*width] == 0xffffffff || tiles[(x+1)+(y)*width] == 0xff00ff0a || tiles[(x+1)+(y)*width] == 0xfffffffa) r = true;
				if(tiles[(x-1)+(y)*width] == 0xffffffff || tiles[(x-1)+(y)*width] == 0xff00ff0a || tiles[(x-1)+(y)*width] == 0xfffffffa) l = true;
				if(tiles[(x)+(y+1)*width] == 0xffffffff || tiles[(x)+(y+1)*width] == 0xff00ff0a || tiles[(x)+(y+1)*width] == 0xfffffffa) d = true;
				if(tiles[(x)+(y-1)*width] == 0xffffffff || tiles[(x)+(y-1)*width] == 0xff00ff0a || tiles[(x)+(y-1)*width] == 0xfffffffa) u = true;
				
				if(l && u) return Tile.dirtTL;
				else if(r && u) return Tile.dirtTR;
				else if(l && d) return Tile.dirtBL;
				else if(r && d) return Tile.dirtBR;
				else if(u) return Tile.dirtU;
				else if(r) return Tile.dirtR;
				else if(d) return Tile.dirtD;
				else if(l) return Tile.dirtL;
				else return Tile.dirt;
			}
			else return Tile.voidT;
		}
	}
	
	public Tile getRTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidT;
		if(tiles[x+y*width] >= 0 && tiles[x+y*width] < 100) {
			if(tiles[x+y*width] >= 0  && tiles[x+y*width] < 20) return Tile.water;
			else if(tiles[x+y*width] >= 20 && tiles[x+y*width] < 75) return Tile.grass;
			else if(tiles[x+y*width] >= 75 && tiles[x+y*width] < 94) return Tile.grassFW;
			else if(tiles[x+y*width] >= 94 && tiles[x+y*width] < 100) return Tile.grass;
			else return Tile.voidT;
		}
		return Tile.voidT;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	private Tile walls(int x, int y) {
		if(x == 0 && (y == 31 || y == 32)) return Tile.teleport;
		else if(x == width && (y == 31 || y == 32)) return Tile.teleport;
		else if(y == 0 && (x == 31 || x == 32)) return Tile.teleport;
		else if(y == height && (x == 31 || x == 32)) return Tile.teleport;
		
		if(Main.data.zone == 5) {
			if(x == 0 && y == 30) return Tile.treeBTile;
			else if(x == width && y == 30) return Tile.treeBTile;
			else if(x == 0 && y == 33) return Tile.treeT2Tile;
			else if(x == width && y == 33) return Tile.treeT2Tile;
			else if(y == -1 && (x == 31 || x == 32)) return Tile.treeBTile;
			else if(y == height+1 && (x == 31 || x == 32)) return Tile.treeT2Tile;
			else if(x == 0) return Tile.treeTTile;
			else if(y == 0 && x > -1 && x < width) return Tile.treeBTile;
			else if(y == height && x > -1 && x < width) return Tile.treeT2Tile;
			else return Tile.treeTTile;
		}
		else if(Main.data.zone == 4) {
			if(x == 0 && y == 30) return Tile.treeBTile;//
			else if(x == width && y == 30) return Tile.treeBTile;//
			else if(x == 0 && y == 33) return Tile.treeT2Tile;//
			else if(x == width && y == 33) return Tile.treeT2Tile;//
			else if(y == -1 && (x == 31 || x == 32)) return Tile.wall;//
			else if(y == height+1 && (x == 31 || x == 32)) return Tile.treeT2Tile;//
			else if(y < -5 && (x == 1 || x == width-1)) return Tile.wall;
			else if(x == 0) return Tile.treeTTile;//Left
			else if(y == 0 && x > -1 && x < width) return Tile.wallRock;//Top middle
			else if(y < 0 && y > -5 && x > -1 && x < width) return Tile.wallRock;
			else if(y == -5 && x > -1 && x < width) return Tile.wall;
			else if(y == height && x > -1 && x < width) return Tile.treeT2Tile;//Bottom middle
			else return Tile.treeTTile;
		}
		else {
			if(x == 0 && y == 30) return Tile.treeBTile;
			else if(x == width && y == 30) return Tile.treeBTile;
			else if(x == 0 && y == 33) return Tile.treeT2Tile;
			else if(x == width && y == 33) return Tile.treeT2Tile;
			else if(y == -1 && (x == 31 || x == 32)) return Tile.treeBTile;
			else if(y == height+1 && (x == 31 || x == 32)) return Tile.treeT2Tile;
			else if(x == 0) return Tile.treeTTile;
			else if(y == 0 && x > -1 && x < width) return Tile.treeBTile;
			else if(y == height && x > -1 && x < width) return Tile.treeT2Tile;
			else return Tile.treeTTile;
		}
	}

}
