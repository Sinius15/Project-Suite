package com.sinius15.suite.game;

import java.awt.Dimension;

public class Level {

	public int w;
	public int h;
	public byte[][] tiles;
	
	public Level(Dimension d) {
		w = d.width;
		h = d.height;
		tiles = new byte[w][h];
	}

	public void tick() {
		if(true)
			return;
	}
	
}
