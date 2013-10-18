package com.sinius15.suite.entitys;

import java.util.ArrayList;
import java.util.List;

import com.sinius15.suite.game.Level;

public abstract class Wagon extends Entity {

	List<Crate> crates;
	
	public Wagon(int x, int y, Level level) {
		super(x, y, level);
		crates = new ArrayList<Crate>();
	}

}
