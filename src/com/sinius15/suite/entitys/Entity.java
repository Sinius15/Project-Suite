package com.sinius15.suite.entitys;

import java.awt.Image;

import com.sinius15.suite.game.Level;

public abstract class Entity {

	public int x;
	public int y;
	public Level level;
	public boolean removed = false;
	
	public Entity(int x,int y,Level level) {
		this.x = x;
		this.y = y;
		this.level = level;
	}

	
	public void remove() {
		removed = true;
	}
	
	public abstract Image render();
	
	public abstract void tick();
	
	public abstract String toSave();
	
	public static Entity loadEntity(String s) {
		s.charAt(2);
		return null;
	}
}
