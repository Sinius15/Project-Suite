package com.sinius15.suite.game;

import java.util.ArrayList;
import java.util.List;

public abstract class Wagon extends Entity {

	List<Crate> crates = new ArrayList<Crate>();
	
	public Wagon(int x, int y, Level level) {
		super(x, y, level);
	}

}
