package com.sinius15.suite.entitys;

import com.sinius15.suite.game.Level;

public abstract class Locomotion extends Wagon {

	public int strenght;
	public String name;
	
	public Locomotion(int x, int y, Level level) {
		super(x, y, level);
	}

}
