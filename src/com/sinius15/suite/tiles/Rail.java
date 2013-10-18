package com.sinius15.suite.tiles;

import com.sinius15.suite.entitys.Train;
import com.sinius15.suite.game.Direction;
import com.sinius15.suite.game.Tile;

public abstract class Rail extends Tile{

	public Rail(int id) {
		super(id);
	}
	
	public abstract Direction out(Direction in);
	
	public abstract boolean canRideOn(Train t);

}
