package com.sinius15.suite.game;

import java.awt.Image;
import java.awt.image.BufferedImage;
/**
 * Just here to return a tile if you go outside the borders
 * @author David
 *
 */
public class SideTile extends Tile {

	public SideTile(byte id) {
		super(id);
	}

	@Override
	public Image render(Level level, int x, int y) {
		return new BufferedImage(0,0,0);
	}

	@Override
	public void tick() {
	}

}
