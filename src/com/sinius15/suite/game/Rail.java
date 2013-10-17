package com.sinius15.suite.game;

public abstract class Rail extends Tile{

	public Rail(int id) {
		super(id);
	}
	
	public abstract Direction out(Direction in);
	
	public abstract boolean canRideOn(Train t);

}
