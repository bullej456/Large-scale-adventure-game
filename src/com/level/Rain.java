package com.level;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Main.Screen;
import com.entity.Drop;

public class Rain {
	
	private Random random = new Random();
	private List<Drop> dropsB = new ArrayList<Drop>();
	private List<Drop> dropsT = new ArrayList<Drop>();
	
	public Rain(int amount) {
		for(int i = 0; i < amount; i++) {
			if(random.nextBoolean()) dropsT.add(new Drop(true));
			else dropsB.add(new Drop(true));
		}
	}
	
	public void update(int xx, int yy) {
		for(Drop d: dropsB) d.update(xx, yy);
		for(Drop d: dropsT) d.update(xx, yy);
	}
	
	public void rainOn(boolean on) {
		if(on) {
			for(Drop d: dropsB) d.rainOn();
			for(Drop d: dropsT) d.rainOn();
		}
		else {
			for(Drop d: dropsB) d.rainOff();
			for(Drop d: dropsT) d.rainOff();
		}
	}
	
	public void renderB(Screen screen) {
		for(Drop d: dropsB) d.render(screen);
	}
	
	public void renderT(Screen screen) {
		for(Drop d: dropsT) d.render(screen);
	}

}
