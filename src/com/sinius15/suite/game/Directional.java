package com.sinius15.suite.game;

public enum Directional {

	LEFTRIGHT(Direction.LEFT,Direction.RIGHT),
	UPDOWN(Direction.UP,Direction.DOWN);
	
	private Direction dirTo;
	private Direction dirFrom;
	
	Directional(Direction dir1 ,Direction dir2) {
		dirTo = dir1;
		dirFrom = dir2;
	}
	
}
