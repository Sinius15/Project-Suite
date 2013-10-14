package com.sinius15.suite.game;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

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
	
	public BufferedImage render() {
		return null;
	}
	
}
