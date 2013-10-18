package com.sinius15.suite.tiles;

import java.awt.Image;
import java.awt.image.BufferedImage;

import com.sinius15.suite.game.Level;
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
