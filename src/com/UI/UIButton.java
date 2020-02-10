package com.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import com.Main.Mouse;
import com.Main.Vector2i;

public class UIButton extends UIComponent{
	
	private UIButtonListener buttonListener;
	private UIActionListener actionListener;
	public UILabel label;
	private boolean inside = false;
	private boolean pressed = false;
	
	private Image image;

	public UIButton(Vector2i pos, Vector2i size, Vector2i textOffset, UIActionListener actionListener) {
		super(pos, size);
		label = new UILabel(new Vector2i(pos.x + textOffset.x + size.x/2, pos.y + textOffset.y + size.y/2), "no");
		this.actionListener = actionListener;
		init();
	}
	
	public UIButton(Vector2i pos, BufferedImage image, UIActionListener actionListener) {
		super(pos, new Vector2i(image.getWidth(), image.getHeight()));
		setImage(image);
		this.actionListener = actionListener;
		init();
	}
	
	private void init() {
		colour = new Color(0xff0d0d0d);
		buttonListener = new UIButtonListener();
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public void setButtonListener(UIButtonListener buttonListener) {
		this.buttonListener = buttonListener;
	}
	
	public void setActionListener(UIActionListener actionListener) {
		this.actionListener = actionListener;
	}
	
	public void update() {
		if(label != null) label.setOffset(offset);
		
		Rectangle rect = new Rectangle(getAbPos().x, getAbPos().y, size.x, size.y);
		if(rect.contains(new Point(Mouse.getX(), Mouse.getY()))) {
			if(!inside)
				buttonListener.entered(this);
			inside = true;
			
			if(!pressed && Mouse.getButton() == MouseEvent.BUTTON1) {
				pressed = true;
				buttonListener.pressed(this);
			}
			else if(pressed && Mouse.getButton() == MouseEvent.NOBUTTON) {
				pressed = false;
				buttonListener.released(this);
				actionListener.perform();
			}
		}
		else {
			if(inside) {
				buttonListener.exited(this);
				pressed = false;
			}
			inside = false;
		}
	}
	
	public void setText(String text) {
		label.text = text;
	}
	
	public void render(Graphics g) {
		int x = pos.x + offset.x;
		int y = pos.y + offset.y;
		if(image != null) {
			g.drawImage(image, x, y, null);
		}
		else {
			g.setColor(colour);
			g.fillRect(x, y, size.x, size.y);
			
			if(label != null) {
				label.render(g);
			}
		}
	}
	
}
