package com.sinius15.suite.entitys;

import java.awt.Image;

import com.sinius15.suite.game.Level;

public class CrateEntity extends Entity {

	public CrateEntity(int x, int y, Level level) {
		super(x, y, level);
	}

	@Override
	public Image render() {
		return null;
	}

	@Override
	public void tick() {

	}

	@Override
	public String toSave() {
		return null;
	}

}
