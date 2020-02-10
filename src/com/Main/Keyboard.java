package com.Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{
	
	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right,
	esc, e, shift, i, space, r, m, n1, n2, n3;
	
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		
		esc = keys[KeyEvent.VK_ESCAPE];
		e = keys[KeyEvent.VK_E];
		shift = keys[KeyEvent.VK_SHIFT];
		i = keys[KeyEvent.VK_I];
		space = keys[KeyEvent.VK_SPACE];
		r = keys[KeyEvent.VK_R];
		m = keys[KeyEvent.VK_M];
		n1 = keys[KeyEvent.VK_1];
		n2 = keys[KeyEvent.VK_2];
		n3 = keys[KeyEvent.VK_3];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
