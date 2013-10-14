package com.sinius15.suite.game;

public enum Direction {

	UP,
	UPRIGHT,
	RIGHT,
	DOWNRIGHT,
	DOWN,
	DOWNLEFT,
	LEFT,
	UPLEFT,
	MIDDLE;
	
	public static Direction getReversed(Direction d) {
		switch (d) {
			case DOWN:
				return UP;
			case DOWNLEFT:
				return UPRIGHT;
			case DOWNRIGHT:
				return UPLEFT;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
			case UP:
				return DOWN;
			case UPLEFT:
				return DOWNRIGHT;
			case UPRIGHT:
				return DOWNLEFT;
			case MIDDLE:
				return MIDDLE;
			default:
				return MIDDLE;
		}
	}
	
}
