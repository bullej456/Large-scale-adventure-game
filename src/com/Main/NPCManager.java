package com.Main;

import java.util.ArrayList;

import com.UI.UIActionListener;
import com.entity.mob.NPC;
import com.graphics.QuestionBox;
import com.level.Level;

public class NPCManager {
	
	private ArrayList<NPC> NPCs = new ArrayList<NPC>();
	private ArrayList<QuestionBox> qbs = new ArrayList<QuestionBox>();
	
	public NPCManager(Level level, int zone) {
		addAll(level, zone);
	}
	
	private void addQB(String text) {
		qbs.add(new QuestionBox(Main.getWindowWidth()/4 - Main.getWindowHeight()/8, Main.getWindowHeight()/8,
				4*Main.getWindowHeight()/15, Main.getWindowHeight()/25,
				text, 2));
	}
	
	private void addAll(Level level, int zone) {
		//create each qb 
		//then add to new npc
		//add each npc to level
		if(zone == 5) {
			addQB("Hello there fellow traveller. You may call me yellow.");
			qbs.get(qbs.size() - 1).setAction(new UIActionListener() {
				public void perform() {
					Main.musicPlayer.playSound(16, -10f);
					qbs.get(qbs.size() - 1).setQText("I can't seem to get past these two rune turrets!", 0);
					qbs.get(qbs.size() - 1).setText("...", 0);
				}
			}, 0);
			qbs.get(qbs.size() - 1).setText("Hello.", 0);
			qbs.get(qbs.size() - 1).setAction(new UIActionListener() {
				public void perform() {
					Main.musicPlayer.playSound(16, -10f);
					qbs.get(qbs.size() - 1).setQText("Good luck to you sir!", 0);
				}
			}, 1);
			qbs.get(qbs.size() - 1).setText("Bye.", 1);
			NPCs.add(new NPC(12, 30, qbs.get(qbs.size() - 1)));
		}
		else if(zone == 4) {
			addQB("1 plains spirit key please.");
			qbs.get(0).setAction(new UIActionListener() {
				public void perform() {
					Main.musicPlayer.playSound(16, -10f);
					qbs.get(0).setQText("...haha...move along.", 0);
					qbs.get(0).setText("...", 0);
					qbs.get(0).setText("...", 1);
				}
			}, 0);
			qbs.get(0).setText("What's that.", 0);
			qbs.get(0).setAction(new UIActionListener() {
				public void perform() {
					Main.musicPlayer.playSound(16, -10f);
					qbs.get(0).setQText("...", 0);
				}
			}, 1);
			qbs.get(0).setText("Bye.", 1);
			NPCs.add(new NPC(30, 2, qbs.get(0), 10));
			
			addQB("1 plains spirit key please.");
			qbs.get(1).setAction(new UIActionListener() {
				public void perform() {
					Main.musicPlayer.playSound(16, -10f);
					qbs.get(1).setQText("...haha...move along.", 0);
					qbs.get(1).setText("...", 0);
					qbs.get(1).setText("...", 1);
				}
			}, 0);
			qbs.get(1).setText("What's that.", 0);
			qbs.get(1).setAction(new UIActionListener() {
				public void perform() {
					Main.musicPlayer.playSound(16, -10f);
					qbs.get(1).setQText("...", 0);
				}
			}, 1);
			qbs.get(1).setText("Bye.", 1);
			NPCs.add(new NPC(33, 2, qbs.get(1), 10));
		}
		
		
		
		for(NPC n: NPCs) {
			level.add(n);
		}
	}

}
