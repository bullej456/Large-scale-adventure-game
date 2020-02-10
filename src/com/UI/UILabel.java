package com.UI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import com.Main.Main;
import com.Main.Vector2i;

public class UILabel extends UIComponent{
	
	public String text;
	private Font font;
	
	public UILabel(Vector2i pos, String text) {
		super(pos);
		font = new Font("Helivetica", Font.PLAIN, 16);
		this.text = text;
		colour = new Color(0xffffffff);
		loadFont();
	}
	
	private void loadFont() {
		try {
		     GraphicsEnvironment ge = 
		         GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./res/Munro.ttf")));
			if(Main.data == null) font = new Font("Munro", Font.PLAIN, 20);
			else if(Main.data.scale == 2) font = new Font("Munro", Font.PLAIN, 16);
			else font = new Font("Munro", Font.PLAIN, 20);
		} catch (IOException|FontFormatException e) {
		     //Handle exception
		}
	}
	
	public UILabel setColour(Color colour) {
		this.colour = colour;
		return this;
	}
	
	public UILabel setFont(Font font) {
		this.font = font;
		return this;
	}
	
	public void render(Graphics g) {
		g.setFont(font);
		g.setColor(colour);
		g.drawString(text, pos.x + offset.x, pos.y + offset.y);
	}

}
