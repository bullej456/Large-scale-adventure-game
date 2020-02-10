package com.graphics;

import java.util.ArrayList;
import java.util.List;

import com.Main.Main;
import com.Main.Vector2i;
import com.UI.UIActionListener;
import com.UI.UIButton;
import com.UI.UIManager;
import com.UI.UIPanel;
import com.entity.Entity;

public class QuestionBox extends Entity{
	
	public List<TextBox> tbs = new ArrayList<TextBox>();
	private UIManager ui;
	private UIPanel answerPanel;
	private int sizeX, sizeY;
	
	public QuestionBox(int x, int y, int sizeX, int sizeY, String text, int numQuestions) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		ui = Main.getUIManager();
		tbs.add(new TextBox(x, y, sizeX, sizeY, text));
		tbs.get(0).show(false);
		answerPanel = new UIPanel(new Vector2i(x, y + tbs.get(0).sizeY + 10), new Vector2i(tbs.get(0).sizeX, numQuestions*Main.getWindowHeight()/20), false);
		
		for(int i = 0; i < numQuestions; i++) {
			UIButton button = new UIButton(new Vector2i(Main.getWindowWidth()/100, Main.getWindowHeight()/60 + i*Main.getWindowHeight()/25),
					new Vector2i(answerPanel.size.x- Main.getWindowHeight()/30, Main.getWindowHeight()/30),
					new Vector2i(-50, 8), new UIActionListener() {
				public void perform(){
					
				}
			});
			answerPanel.addComponent(button);
		}
		ui.addPanel(answerPanel);
	}
	
	public void update() {
		for(TextBox t: tbs) {
			if(t.panel.show) {
				answerPanel.show = true;
				break;
			}
			else answerPanel.show = false;
		}
	}
	
	public void setQText(String text, int id) {
		if(id >= tbs.size()) {
			System.err.println("id is out of bounds!");
			return;
		}
		
		tbs.get(id).setText(text);
	}
	
	public void setText(String text, int id) {
		if(id >= answerPanel.getComponents().size()) {
			System.err.println("id is out of bounds!");
			return;
		}
		
		if(answerPanel.getComponents().get(id) instanceof UIButton) {
			((UIButton)answerPanel.getComponents().get(id)).setText(text);
		}
	}
	
	public void setAction(UIActionListener al, int id) {
		if(id >= answerPanel.getComponents().size()) return;
		
		if(answerPanel.getComponents().get(id) instanceof UIButton) {
			((UIButton)answerPanel.getComponents().get(id)).setActionListener(al);
		}
	}
	
	public void add(TextBox tb) {
		tbs.add(tb);
	}
	
	public void add(String text) {
		tbs.add(new TextBox((int)x, (int)y, sizeX, sizeY, text));
	}
	
	public UIPanel getAnswerPanel() {
		return answerPanel;
	}

}
