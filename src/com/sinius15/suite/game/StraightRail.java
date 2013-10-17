package com.sinius15.suite.game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class StraightRail extends Rail {

	public int strenght;
	public Direction direction1;
	public Direction direction2;
	private Image img;
	
	public StraightRail(int id,int str) {
		super(id);
		strenght = str;
		try {
			img = ImageIO.read(new File("res/railStraight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
	public Image render(Level level, int x, int y) {
		return img;
	}

	@Override
	public void tick() {

	}

}
