package com.sinius15.suite.entitys;

import java.util.ArrayList;
import java.util.List;

import com.sinius15.suite.game.Direction;
import com.sinius15.suite.game.Level;

public abstract class Train extends Entity {
	
	public int weight;
	public int speed;
	public Direction d;
	List<Wagon> wagons = new ArrayList<Wagon>();
	Locomotion loco;
	
	public Train(int x, int y, Level level) {
		super(x, y, level);
	}
	
	
	
	
}
