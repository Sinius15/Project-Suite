package com.sinius15.suite.game;

import java.awt.Image;

public abstract class Tile {

	public static Tile[] tiles = new Tile[255];
	public static final Tile sideTile = new SideTile((byte)0);
	
	
	public final int id;
	
	public Tile(int id) {
		if(tiles[id]!=null)
			throw new IllegalArgumentException("Id already exists");
		
		this.id = id;
		tiles[this.id] = this;
	}
	
	public abstract Image render();
	
	//Mabye to remove
	public abstract void tick();
	
	
}
