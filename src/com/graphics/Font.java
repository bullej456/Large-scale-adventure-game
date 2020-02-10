package com.graphics;

import com.Main.Screen;

public class Font {
	
	private boolean fixed = false;
	
	private static SpriteSheet font = new SpriteSheet("/textures/fonts/fontSheet.png", 8);
	private static Sprite[] characters = Sprite.split(font);
	
	private static String charIndex = "abcdefghijkl" + //
										"mnopqrstuvwx" + //
											"yz 012345678" + //
										"9,.!?+";

	public Font() {
		
	}
	
	public void setFixed(boolean fixed) {
		this.fixed = fixed;
	}
	
	public void render(int x, int y, String text, Screen screen, int color) {
		render(x, y, color, text, screen);
	}
	
	public void render(int x, int y, int colour, String text, Screen screen) {
		int offset = 0;
		int newline = 0;
		int xAdd = 0;
		for(int i = 0; i < text.length(); i++) {
			char currentChar = text.charAt(i);
			if(currentChar == '\n') {
				newline++;
				xAdd = 0;
				continue;
			}
			int index = charIndex.indexOf(currentChar);
			//if(index == 11 || index == 14 || index == 17 || index == 3) offset += 2;
			screen.renderTextChar(x + 7*xAdd - offset, y + newline*10, characters[index], colour, fixed);
			xAdd++;
		}
	}
	
}
