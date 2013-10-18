package com.sinius15.suite.entitys;

import java.awt.Image;

public class Crate {

	public int weight;
	public String name;
	public boolean explosive = false;
	public static Image mainCrate;
	
	static {
		
	}
	
	public Crate(String name,int wieght) {
		this.name = name;
		weight = wieght;
	}
	
	public void setExplosive(boolean b) {
		explosive = b;
	}
	
	public Image render() {
		return mainCrate;
	}
	
}
