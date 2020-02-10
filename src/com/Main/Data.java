package com.Main;

import java.io.Serializable;

import com.entity.mob.Player;
import com.item.HandItem;
import com.item.StickItem;
import com.item.SwordItem;

public class Data implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public int width, height, scale;
	public float stamina, maxStamina, xp, nextXp, health, maxHealth;
	public int garlic, strawberry, pflower, rflower;
	public int dummyNum, chaserNum;
	public int playerX, playerY;
	public int level;
	public int zone;
	public int item1, item2, item3;
	//Blueprints are discovered from things like chests, then can be created by blacksmith etc.
	public boolean chest1, chest2, chest3;
	
	public Data() {
		zone = -1;
		scale = 3;
		width = 450;
		height = width / 16 * 9;
		stamina = maxStamina = 150;
		health = maxHealth = 100;
		garlic = strawberry = pflower = rflower = 0;
		dummyNum = chaserNum = 0;
		playerX = -1;
		playerY = -1;
		xp = 0;
		nextXp = 100;
		level = 0;
		
		item1 = 1;
		item2 = 0;
		item3 = 0;
		
		chest1 = false;
		chest2 = false;
		chest3 = false;
	}
	
	public void setItems(Player player) {
		if(player.getItem(1) instanceof StickItem) item1 = 1;
		else if(player.getItem(1) instanceof HandItem) item1 = 2;
		else if(player.getItem(1) instanceof SwordItem) item1 = 3;
		else item1 = 0;
		if(player.getItem(2) instanceof StickItem) item2 = 1;
		else if(player.getItem(2) instanceof HandItem) item2 = 2;
		else if(player.getItem(2) instanceof SwordItem) item2 = 3;
		else item2 = 0;
		if(player.getItem(3) instanceof StickItem) item3 = 1;
		else if(player.getItem(3) instanceof HandItem) item3 = 2;
		else if(player.getItem(3) instanceof SwordItem) item3 = 3;
		else item3 = 0;
	}

}
