package com.sinius15.suite.game;

public enum Directional {

	LEFTRIGHT(Direction.LEFT,Direction.RIGHT),
	UPDOWN(Direction.UP,Direction.DOWN),
	LUPRDOWN(Direction.UPLEFT,Direction.DOWNRIGHT),
	RUPLDOWN(Direction.UPRIGHT,Direction.DOWNLEFT);
	
	public Direction dirTo;
	public Direction dirFrom;
	
	Directional(Direction dir1 ,Direction dir2) {
		dirTo = dir1;
		dirFrom = dir2;
	}
	
}
