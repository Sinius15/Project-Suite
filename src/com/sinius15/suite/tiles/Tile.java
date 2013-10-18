package com.sinius15.suite.tiles;

import java.awt.Image;

import com.sinius15.suite.game.Level;

public abstract class Tile {

	public static Tile[] tiles = new Tile[255];			//all the avalable tiles
	public static final Tile sideTile = new SideTile((byte)0);
	
	
	public final int id;  	//id = 0    =>  air
	
	public Tile(int id) {
		if(tiles[id]!=null)
			throw new IllegalArgumentException("Id already exists");
		
		this.id = id;
		tiles[this.id] = this;
	}
	
	public abstract Image render(Level level, int x, int y);
	
	//Mabye to remove
	public abstract void tick();
	
	
}
