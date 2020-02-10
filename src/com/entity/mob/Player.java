package com.entity.mob;

import java.awt.Color;
import java.awt.Font;

import com.Main.Keyboard;
import com.Main.Main;
import com.Main.Screen;
import com.Main.Vector2i;
import com.Main.XPManager;
import com.UI.Inventory;
import com.UI.UIActionListener;
import com.UI.UIButton;
import com.UI.UILabel;
import com.UI.UIManager;
import com.UI.UIPanel;
import com.UI.WorldMap;
import com.entity.Entity;
import com.entity.projectile.Projectile;
import com.entity.spawner.ParticleSpawner;
import com.graphics.AnimatedSprite;
import com.graphics.FloatFont;
import com.graphics.Sprite;
import com.graphics.SpriteSheet;
import com.item.HandItem;
import com.item.Item;
import com.item.StickItem;
import com.item.SwordItem;

public class Player extends Mob{
	
	public Keyboard input;
	private Sprite sprite;
	private double speed = 1.1;
	private boolean canRun = true;
	public static boolean itemChange = true;
	private Item item1, item2, item3;
	private int itemNum = 1;
	
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.playerU, 16, 16, 4);
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.playerD, 16, 16, 4);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.playerL, 16, 16, 4);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.playerR, 16, 16, 4);
	private AnimatedSprite animSprite = null;
	
	private UIManager ui;
	private Inventory inventory;
	private WorldMap map;
	private int W = Main.getWindowWidth();
	private int H = Main.getWindowHeight();
	public static XPManager xpm;
	
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		fireRate = 10;
		sprite = Sprite.playerD;
		animSprite = down;
		xpm = new XPManager();
		setUI();

		setItems();
		item = item1;
	}
	
	private void setItems() {
		item1 = loadItem(Main.data.item1);
		if(item1 != null) item1.pickup(this);
		item2 = loadItem(Main.data.item2);
		if(item2 != null) item2.pickup(this);
		item3 = loadItem(Main.data.item3);
		if(item3 != null) item3.pickup(this);
	}
	
	private Item loadItem(int n) {
		if(n == 1) return new StickItem(0, 0);
		else if(n == 2) return new HandItem(0, 0);
		else if(n == 3) return new SwordItem(0, 0);
		return null;
	}
	
	private void setUI() {
		ui = Main.getUIManager();
		Font font = new Font("Helivetica", Font.PLAIN, 16);
		
		map = new WorldMap(ui, input);
		
		//UIPanels (not used)
		UIPanel panel = new UIPanel(new Vector2i(0, 7*H/8), new Vector2i(W, H/8), true);
		panel.colour = new Color(50, 50, 50, 180);
		UIPanel panel1 = new UIPanel(new Vector2i(W/80, H/100), new Vector2i(W/3 - 2*W/80, H/10), true);
		panel1.addComponent(new UILabel(new Vector2i(H/60, H/30), "Stats:").setFont(font));
		UIPanel panel2 = new UIPanel(new Vector2i(W/3 + W/80, H/100), new Vector2i(W/3 - 2*W/80, H/10), true);
		panel2.addComponent(new UILabel(new Vector2i(H/60, H/30), "Job:").setFont(font));
		UIPanel panel3 = new UIPanel(new Vector2i(2*W/3 + W/80, H/100), new Vector2i(W/3 - 2*W/80, H/10), true);
		
		//Inventory set.
		inventory = new Inventory(ui, input);
		
		//Buttons (not used)
		UIButton button = new UIButton(new Vector2i(W/80, H/80), new Vector2i(H/6, H/30), new Vector2i(-H/15, H/100),
				new UIActionListener() {
			public void perform() {
				if(inventory.inventoryPanel.show) inventory.inventoryPanel.show = false;
				else inventory.inventoryPanel.show = true;
			}
		});
		button.setText("Inventory (i)");
		panel3.addComponent(button);
		UIButton button2 = new UIButton(new Vector2i(W/80, 2*H/80 + H/30), new Vector2i(H/6, H/30), new Vector2i(-H/15, H/100),
				new UIActionListener() {
			public void perform() {
				Main.qb.tbs.get(0).show(!(Main.qb.tbs.get(0).isShowing()));
			}
		});
		button2.setText("Escape (esc)");
		panel3.addComponent(button2);
		
		//Image button test.
//		try {
//			UIButton imageButton = new UIButton(new Vector2i(W/50, H/20), ImageIO.read(new File("res/textures/sheets/heart.png")),
//					new UIActionListener() {
//				public void perform() {
//					System.out.println("heart");
//				}
//			});
//			panel1.addComponent(imageButton);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		panel.addComponent(panel1);
		panel.addComponent(panel2);
		panel.addComponent(panel3);

		//ui.addPanel(panel);
	}
	
	public void update() {
		itemUpdate();
		xpm.update();
		map.update();
		//xpBar.setProgress(Main.data.xp/Main.data.nextXp);
		
		if(input.e) itemChange = true;
		running();
		
		animSprite.update();
		//if(walking) anim.update();
		//else setFrame(0);
		double xa = 0, ya = 0;
		
		if(input.up) {
			animSprite = up;
			ya -= speed;
		}
		else if(input.down) {
			animSprite = down;
			ya += speed;
		}
		if(input.left) {
			animSprite = left;
			xa -= speed;
		}
		else if(input.right) {
			animSprite = right;
			xa += speed;
		}
		
		if(xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		}
		else walking = false;
		
		clear();
		updateShooting();
		inventory.update();
		itemChange = false;
		if(hit) checkHit();
		
		Main.data.playerX = (int) getX();
		Main.data.playerY = (int) getY();
		Main.data.setItems(this);
	}
	
	protected void checkHit() {
		Main.data.health -= hitDamage;
		Main.musicPlayer.playSound(11, -5f);
		level.add(new FloatFont(((int)(x + random.nextInt(6)-3) >> 4), ((int)y >> 4), "" + hitDamage, 40, 0xffffffff));
		level.add(new ParticleSpawner((int)x, (int)y, 240, 8, level, true));
		hit = false;
		hitDamage = 0;
		if(Main.data.health <= 0) System.out.println("DEAD");//Replace with dead();.
	}
	
	private void itemUpdate() {
		if(input.n1) {
			itemNum = 1;
			item = item1;
		}
		else if(input.n2){
			itemNum = 2;
			item = item2;
		}
		else if(input.n3){
			itemNum = 3;
			item = item3;
		}
		
		if(item != null) {
			item.setUse(input.space);
			item.update();
		}
		if(input.r && item != null) {
			item.drop();
			level.add(item);
			if(itemNum == 1) item1 = null;
			else if(itemNum == 2) item2 = null;
			else if(itemNum == 3) item3 = null;
			item = null;
		}
	}
	
	public void pickUp(Item item) {
		if(itemNum == 1) item1 = item;
		else if(itemNum == 2) item2 = item;
		else if(itemNum == 3) item3 = item;
		this.item = item;
		this.item.pickup(this);
		Main.musicPlayer.playSound(18, -10f);
	}
	
	public Item getItem(int n) {
		if(n == 1) return item1;
		else if(n == 2) return item2;
		else return item3;
	}
	
	private void running() {
		if(Main.data.stamina >= 10) canRun = true;
		if(input.shift && canRun) {
			speed = 2;
			Main.data.stamina -= 0.7;
			if(Main.data.stamina < 0) {
				canRun = false;
				Main.data.stamina = 0;
			}
			down.setFrameRate(6);
			up.setFrameRate(6);
			left.setFrameRate(6);
			right.setFrameRate(6);
		}
		else{
			speed = 1.1;
			Main.data.stamina += 0.2;
			if(Main.data.stamina > Main.data.maxStamina) Main.data.stamina = Main.data.maxStamina;
			down.setFrameRate(10);
			up.setFrameRate(10);
			left.setFrameRate(10);
			right.setFrameRate(10);
		}
	}
	
	private void clear() {
		for(int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if(p.isRemoved()) level.getProjectiles().remove(i);
		}
	}
	
	private void updateShooting() {
		if(fireRate > 0) fireRate--;
//		if(Mouse.getButton() == 1 && fireRate <= 0) {
//			double dx = Mouse.getX() - (Main.getWindowWidth()/2);
//			double dy = Mouse.getY() - (Main.getWindowHeight()/2);
//			double dir = Math.atan2(dy, dx);
//			shoot(x, y, dir);
//			fireRate = Bullet.FIRE_RATE;
//		}
		if(input.space && fireRate <= 0) {
			double direction = -1;
			if(dir == Dir.UP) direction = -Math.PI/2;
			else if(dir == Dir.DOWN) direction = Math.PI/2;
			else if(dir == Dir.LEFT) direction = Math.PI;
			else if(dir == Dir.RIGHT) direction = 2*Math.PI;
			shoot(x, y, direction);
		}
	}
	
	private int t = 0;
	public void render(Screen screen) {
		double xx = 0;
		double yy = 0;
		xx = x - (Sprite.playerD.SIZE/2);
		yy = y - (Sprite.playerD.SIZE/2);
		sprite = animSprite.getSprite();
		if(hit) sprite = Sprite.lightBall;
		if(dir == Dir.UP || dir == Dir.LEFT) if(item != null) item.render(screen);
		screen.renderMob((int)xx, (int)yy, sprite);
		if(dir == Dir.DOWN || dir == Dir.RIGHT) if(item != null) item.render(screen);
		
		t++;
		if(input.shift && canRun && t > 5) {
			new ParticleSpawner((int)x, (int)y+4, 10, 1, level);
			t = 0;
//			if(dir == Dir.UP) screen.renderSprite((int)xx, (int)yy+16, Sprite.redBall, true);
//			else if(dir == Dir.LEFT) screen.renderSprite((int)xx+16, (int)yy, Sprite.redBall, true);
//			else if(dir == Dir.RIGHT) screen.renderSprite((int)xx-16, (int)yy, Sprite.runRight, true);
//			else if(dir == Dir.DOWN) screen.renderSprite((int)xx, (int)yy-16, Sprite.redBall, true);
		}
	}
	
	public void setPlayerTilePos(int xt, int yt) {
		x = xt*16;
		y = yt*16;
	}
	
	protected boolean collision(double xa, double ya) {
		boolean solid = false;
		//Checks all corners of tiles in front, not just tile in front of origin.
		for(int c = 0; c < 4; c++) {
			int xt = (int) (((x + xa) + c%2*11 - 6)/16);
			int yt = (int) (((y + ya) + c/2*8 - 0)/16);
			
			if(level.getTile(xt, yt).solid()) solid = true;
			
			for(Entity e: level.getEntities()) {
				if(e.equals(this)) break;
				if(e instanceof Mob) {
					//FILL...
				}
				//else {
					if(e.getTileX() == xt && e.getTileY() == yt) {
						if(e.solid()) {
							solid = true;
							break;
						}
					}
				//}
			}
		}
		return solid;
	}

}
