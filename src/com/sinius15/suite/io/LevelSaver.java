package com.sinius15.suite.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import com.sinius15.suite.game.Level;

public class LevelSaver {

	public static void saveLevel(Level l, File f) throws IOException{
		if(!f.exists())
			f.createNewFile();
		PrintWriter writer = new PrintWriter(f);
		
		writer.println("#This is a save file for the game 'Suite'. If you want to open this file, i reccommand downloading the game for free at ...");
		writer.println("");
		writer.println("#general:");
		writer.println("levelName: " + l.g);
		
		writer.close();
		
		String s = "x:" + 10 + "y:" + 10 +  "t:" + l.getTile(10,10).id  + "(" + l.getData(10, 10) + ") " + "e:" + l.entities; 
		
		
	}
	
	public Level loadLevel(File f){

		return null;
	}
	
	/*	This is how the files are going to look:
	 * 
	 *  general:
	 * 	LevelWidht: 100
	 *  LevelHeight: 100
	 *  LevelDificulty: 0 
	 *  XScroll: 20
	 *  YScroll: 20
	 *  Background: images/cookeis.jpg
	 *  StaticBackground: ?
	 *  
	 *  blocks:
	 *  x:12, y:13, t:track(arguments) e:cookiemonster(argumetns) e:cloudmonster(arguments) 
	 *  x:16, y:24, t:track(arguments) e:cookiemonster(argumetns) e:cloudmonster(arguments) 
	 *  */
	
	/*
	 *  t = tile    e = entity
	 *  if a coord=xyz  is not saved,  it has no entitys and the tyle = air)
	 *  The arguments are provided by the getArguments() method. The name is found by the class name.
	 *  This means that all the entitys need all to be in the same package!!!
	 *  
	 *  the arguments are passed in as strings or integers in the constructor of the tile/entity
	 *  
	 * */
}
