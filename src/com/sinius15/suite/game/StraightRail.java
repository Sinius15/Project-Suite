package com.sinius15.suite.game;

import java.awt.Image;

public class StraightRail extends Rail {

	public int strenght;
	public Direction direction1;
	public Direction direction2;
	
	public StraightRail(byte id,int str) {
		super(id);
		strenght = str;
	}

	@Override 
	public Direction out(Direction in) {
		return Direction.getReversed(in);
	}

	@Override
	public boolean canRideOn(Train t) {
		return t.weight < strenght;
	}

	@Override
	public Image render() {
		return null;
	}

	@Override
	public void tick() {

	}

}
