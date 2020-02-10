package com.level;

import com.Main.Vector2i;

public class Node {
	
	public Vector2i tile;
	public Node parent;
	public double f, g, h;
	
	public Node(Vector2i tile, Node parent, double g, double h) {
		this.tile = tile;
		this.parent = parent;
		this.g = g;
		this.h = h;
		this.f = this.g + this.h;
	}

}
