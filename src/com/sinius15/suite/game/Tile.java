package com.sinius15.suite.game;

import java.awt.Image;

public abstract class Tile {

	public static Tile[] tiles = new Tile[255];
	
	private final byte id;
	
	public Tile(byte id) {
		if(tiles[id]!=null)
			throw new IllegalArgumentException("Id already exists");
		
		this.id = id;
		tiles[this.id] = this;
	}
	
	public abstract Image render();
	
	//Mabye to remove
	public abstract void tick();
	
	
}
