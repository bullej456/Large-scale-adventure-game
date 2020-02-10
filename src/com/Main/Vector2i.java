package com.Main;

public class Vector2i {
	
	public int x, y;
	
	public Vector2i(int x, int y) {
		set(x, y);
	}
	
	public Vector2i() {
		set(0, 0);
	}
	
	public void add(Vector2i a) {
		x += a.x;
		y += a.y;
	}
	
	public Vector2i joint(Vector2i a) {
		return new Vector2i(x+a.x, y+a.y);
	}
	
	public void addInt(int a, int b) {
		x += a;
		y += b;
	}
	
	public boolean equals(Vector2i b) {
		if(b.x == x && b.y == y) return true;
		else return false;
	}
	
//	public int x() {
//		return x;
//	}
//	
//	public int y() {
//		return y;
//	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

}
