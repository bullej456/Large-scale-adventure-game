package com.Main;

public class InventorySlot {
	
	private String thing;
	private int num = 0;
	private Ingredient i;

	public enum Ingredient{
		GARLIC, STRAWBERRY, PFLOWER, RFLOWER
	}
	
	public InventorySlot(String thing, Ingredient i) {
		this.thing = thing;
		this.i = i;
	}
	
	public void update() {
		if(i == Ingredient.GARLIC) num = Main.data.garlic;
		if(i == Ingredient.STRAWBERRY) num = Main.data.strawberry;
		if(i == Ingredient.PFLOWER) num = Main.data.pflower;
		if(i == Ingredient.RFLOWER) num = Main.data.rflower;
	}
	
	public String getText() {
		return thing;
	}
	
	public int getNum() {
		return num;
	}
}
