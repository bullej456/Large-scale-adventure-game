package com.UI;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.Main.InventorySlot;
import com.Main.InventorySlot.Ingredient;
import com.Main.Keyboard;
import com.Main.Main;
import com.Main.Vector2i;
import com.entity.mob.Player;

public class Inventory {

	private boolean iLast = false;
	private Keyboard input;
	private List<InventorySlot> ingredients = new ArrayList<InventorySlot>();
	private List<UIPanel> ingPanels = new ArrayList<UIPanel>();
	public UIPanel inventoryPanel;

	private int W = Main.getWindowWidth();
	private int H = Main.getWindowHeight();
	
	public Inventory(UIManager ui, Keyboard input) {
		this.input = input;
		
		ingredients.add(new InventorySlot("Garlic", Ingredient.GARLIC));
		ingredients.add(new InventorySlot("Strawberry", Ingredient.STRAWBERRY));
		ingredients.add(new InventorySlot("Purple Flower", Ingredient.PFLOWER));
		ingredients.add(new InventorySlot("Red Flower", Ingredient.RFLOWER));
		
		inventoryPanel = new UIPanel(new Vector2i(W/20, H/9), new Vector2i(H/2, H/2), false);
//		inventoryPanel.colour = new Color(50, 50, 50);
		UIPanel ingPanel = new UIPanel(new Vector2i(H/60, H/60), new Vector2i(inventoryPanel.size.x - H/30, inventoryPanel.size.y - H/30), true);
		
		inventoryPanel.addComponent(ingPanel);
		ui.addPanel(inventoryPanel);
		

//		UIPanel ingPanel = new UIPanel(new Vector2i(W/20, H/10), new Vector2i(H/2, H/2), true);
//		ui.addPanel(ingPanel);
	}
	
	public void update() {
		if(!input.i && iLast) {
			if(inventoryPanel.show) inventoryPanel.show = false;
			else inventoryPanel.show = true;
		}
		iLast = input.i;
		
		
		
		if(Player.itemChange) {
			for(InventorySlot i: ingredients) {
				i.update();
				if(true) {
					boolean added = false;
					int index = 0;
					for(UIPanel p: ingPanels) {
						if(((UILabel)p.getComponents().get(0)).text.startsWith(i.getText())) {
							
							((UILabel)p.getComponents().get(0)).text = i.getText() + ": " + i.getNum();
							added = true;
							break;
						}
						index++;
					}
					if(!added && i.getNum() > 0) {
						add(i);
					}
					else if(added && i.getNum() == 0){
						inventoryPanel.getComponents().remove(index);
					}
				}
			}
		}
	}
	
	public void add(InventorySlot i) {
		UIPanel panel = new UIPanel(new Vector2i(H/30, ingPanels.size()*(H/21) + H/16), new Vector2i(H/3, H/25), true);
		panel.colour = new Color(0, 0, 0);
		UILabel label = new UILabel(new Vector2i(H/150, (H/37)), i.getText() + ": " + i.getNum());
		panel.addComponent(label);
		if(false) {
			try {
				UIButton imageButton = new UIButton(new Vector2i(W/20, 0), ImageIO.read(new File("res/textures/sheets/heart.png")),
						new UIActionListener() {
					public void perform() {
						
					}
				});
				panel.addComponent(imageButton);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ingPanels.add(panel);
		inventoryPanel.addComponent(ingPanels.get(ingPanels.size()-1));
	}

}
