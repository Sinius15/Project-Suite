package com.sinius15.suite.game;

import java.awt.Image;

public class SideTile extends Tile {

	public SideTile(byte id) {
		super(id);
	}

	@Override
	public Image render(Level level, int x, int y) {
		return null;
	}

	@Override
	public void tick() {
	}

}
