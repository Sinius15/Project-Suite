package com.sinius15.suite.game;

public class Crate {

	public int weight;
	public String name;
	public boolean explosive = false;
	
	public Crate(String name,int wieght) {
		this.name = name;
		weight = wieght;
	}
	
	public void setExplosive(boolean b) {
		explosive = b;
	}
	
}
