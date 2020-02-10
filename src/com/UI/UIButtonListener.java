package com.UI;

import java.awt.Color;

public class UIButtonListener {
	
	public void entered(UIButton button) {
		button.colour = new Color(0xff262626);
	}
	public void exited(UIButton button) {
		button.colour = new Color(0xff0d0d0d);
	}
	
	public void pressed(UIButton button) {
		button.colour = new Color(0xff000000);
	}
	public void released(UIButton button) {
		button.colour = new Color(0xff0d0d0d);
	}

}
