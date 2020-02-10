package com.Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;

import com.UI.UIActionListener;
import com.UI.UIManager;
import com.UI.UIScreen;
import com.audio.MusicPlayer;
import com.entity.mob.Player;
import com.graphics.QuestionBox;
import com.level.Level;
import com.level.LevelManager;
import com.level.TileCoor;

public class Main extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	private static int width;
	private static int height;
	private static int scale = 3;
	private boolean escReleased = false;
	private Thread thread, musicThread;
	private JFrame frame;
	private Keyboard key;
	private TileCoor tileCoor;
	public static boolean running = false;
	private Screen screen;
	private static UIManager uiManager;
	private static UIScreen uiScreen;
	public static Data data;
	public static QuestionBox qb;
	private BufferedImage image;
	private int[] pixels;
	
	public static MusicPlayer musicPlayer;
	public static LevelManager lm;
	public static StartMenu sm;
	
	public Main() {
		data = new Data();
		loadData();
		
		width = data.width;
		height = data.height;
		scale = data.scale;
		
		image = new BufferedImage(width,height,
				BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		//////
		
		Dimension size = new Dimension(width*scale, height*scale);
		setPreferredSize(size);
		
		screen = new Screen(width, height);
		uiManager = new UIManager();
		sm = new StartMenu();
		frame = new JFrame();
		key = new Keyboard();
		musicPlayer = new MusicPlayer("Wood_Forest_SOUND_EFFECT_-_Ambience_Background_Wal");
		
		tileCoor = new TileCoor(29, 5);
		if(data.playerX == -1 && data.playerY == -1) {
			data.playerX = tileCoor.x();
			data.playerY = tileCoor.y();
		}
		lm = new LevelManager(new Player(data.playerX, data.playerY, key));
		
		uiScreen = new UIScreen(lm.getPlayer());
		
		escapeMenu();
		addKeyListener(key);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	private void escapeMenu() {
		qb = new QuestionBox(getWindowWidth()/2 - getWindowHeight()/8, getWindowHeight()/8, 4*getWindowHeight()/15, getWindowHeight()/25,
				"Game escape menu: Please select from below.", 6);
		
		qb.setAction(new UIActionListener() {
			public void perform() {
				musicPlayer.playSound(2, 0);
				System.exit(0);
			}
		}, 0);
		qb.setText("EXIT", 0);
		
		qb.setAction(new UIActionListener() {
			public void perform() {
//				data.playerX = (int) player.getX();
//				data.playerY = (int) player.getY();
				saveData();
				qb.setQText("Game saved! Previous save overwritten.", 0);
				musicPlayer.playSound(2, 0);
			}
		}, 1);
		qb.setText("SAVE", 1);
		
		qb.setAction(new UIActionListener() {
			public void perform() {
				qb.tbs.get(0).show(false);
				musicPlayer.playSound(2, 0);
			}
		}, 2);
		qb.setText("Back", 2);
		
		qb.setAction(new UIActionListener() {
			public void perform() {
				data.scale = 3;
				qb.setQText("Resolution chosen. Please SAVE and restart game to see changes.", 0);
				musicPlayer.playSound(2, 0);
			}
		}, 3);
		qb.setText("1350x760", 3);
		
		qb.setAction(new UIActionListener() {
			public void perform() {
				data.scale = 2;
				qb.setQText("Resolution chosen. Please SAVE and restart game to see changes.", 0);
				musicPlayer.playSound(2, 0);
			}
		}, 4);
		qb.setText("900x506", 4);
		
		qb.setAction(new UIActionListener() {
			public void perform() {
				musicPlayer.playSound(2, 0);
				data = new Data();
				saveData();
				System.out.println("Data set to default.");
				System.exit(0);
				qb.setQText("Save deleted. please restart to start new game.", 0);
			}
		}, 5);
		qb.setText("NEW GAME", 5);
	}
	
	private void saveData() {
		try {
	         FileOutputStream fileOut =	
	         new FileOutputStream("data.bin");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(data);
	         out.close();
	         fileOut.close();
	         System.out.println("Serialized data is saved in /data.txt");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	private void loadData() {
		try {
	         FileInputStream fileIn = new FileInputStream("data.bin");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         data = (Data) in.readObject();
	         System.out.println("Data.bin loaded into data.");
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c) {
	         System.out.println("Data class not found");
	         c.printStackTrace();
	         return;
	      }
	}
	
	public static Level getLevel() {
		return lm.getLevel();
	}
	
	public static UIManager getUIManager() {
		return uiManager;
	}
	
	public static int getWindowWidth() {
		return width*scale;
	}
	
	public static int getWindowHeight() {
		return height*scale;
	}
	
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
		musicThread = new Thread(musicPlayer, "Music");
		musicThread.start();
	}
	
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		try {
			musicThread.join();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		//This is called as this class implements runnable. A game loop is created which
		//calls update 60fps, and render as much as possible.
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		//int updates = 0;
		requestFocus();
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				update();
				//updates++;
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle("FPS: "+frames);
				//updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public void update() {
		if(sm.running) sm.update();
		else{
			lm.update();
			uiScreen.update();
			
			if(!key.esc && escReleased) {
				escReleased = false;
				if(!qb.tbs.get(0).isShowing()) {
					qb.tbs.get(0).show(true);
					Main.musicPlayer.playSound(2, -0f);
				}
				else{
					qb.tbs.get(0).show(false);
					Main.musicPlayer.playSound(3, -0f);
					qb.setQText("Game escape menu: Please select from below.", 0);
				}
			}
			if(key.esc) escReleased = true;
			qb.update();
		}
		uiManager.update();
		key.update();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		screen.clear();
		if(sm.running) sm.render(screen);
		else lm.render(screen, uiScreen);
		for(int i = 0; i< pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image,  0, 0, getWidth(),  getHeight(), null);
		if(sm.running) sm.renderG(g);
		else {
			if(qb.tbs.get(0).isShowing()) {
				g.setColor(new Color(0, 0, 0, 150));
				g.fillRect(0, 0, getWindowWidth(), getWindowHeight());
			}
		}
		uiManager.render(g);
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		main.frame.setResizable(false);
		main.frame.setTitle("");
		main.frame.add(main);
		main.frame.pack();
		main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.frame.setVisible(true);
		
		main.start();
		
	}

}
